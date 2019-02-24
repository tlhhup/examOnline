package org.tlh.exam.user.service;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.auth.enums.UserType;
import org.tlh.exam.auth.feign.AuthUserClient;
import org.tlh.exam.auth.model.req.UserAddDto;
import org.tlh.exam.auth.model.resp.ResponseDto;
import org.tlh.exam.auth.model.resp.UserRespDto;
import org.tlh.exam.user.entity.Admin;
import org.tlh.exam.user.model.req.AdminAddDto;
import org.tlh.exam.user.model.req.AdminReqDto;
import org.tlh.exam.user.model.resp.AdminRespDto;
import org.tlh.exam.user.repository.AdminRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/6
 * <p>
 * Github: https://github.com/tlhhup
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AuthUserClient authUserClient;

    public Page<AdminRespDto> list(String userName, Pageable pageable) {
        Page<Admin> page = this.adminRepository.findAll(pageable);
        List<AdminRespDto> admins = page.stream().map(user -> {
            AdminRespDto admin = new AdminRespDto();
            BeanUtils.copyProperties(user, admin);
            admin.setActive(user.getIsActive());

            ResponseDto<UserRespDto> userDetail = this.authUserClient.getUserDetail(user.getAuthId());
            if (userDetail != null && userDetail.getData() != null) {
                admin.setUserName(userDetail.getData().getUserName());
            }
            return admin;
        }).collect(Collectors.toList());
        return new PageImpl<>(admins, pageable, page.getTotalElements());
    }


    @Transactional
    public boolean update(int id, AdminReqDto adminReqDto) {
        try {
            Admin admin = new Admin();
            BeanUtils.copyProperties(adminReqDto, admin);
            admin.setIsActive(adminReqDto.isActive());
            admin.setId(id);
            this.adminRepository.save(admin);
            return true;
        } catch (BeansException e) {
            log.info("update Admin error", e);
        }
        return false;
    }

    // todo 分布式事务处理

    @Transactional
    public boolean create(AdminAddDto adminAddDto) {
        try {
            Date createTime = DateTime.now().toDate();
            //1.认证中心添加认证信息
            UserAddDto userAddDto = new UserAddDto();
            userAddDto.setUserName(adminAddDto.getUserName());
            userAddDto.setPassword(adminAddDto.getPassword());
            userAddDto.setActive(adminAddDto.isActive());
            userAddDto.setCreateTime(createTime);
            userAddDto.setUserType(UserType.ADMIN);
            ResponseDto<UserRespDto> addUser = this.authUserClient.addUser(userAddDto);
            if (addUser != null && addUser.getData() != null) {
                //2.保存到本地服务
                Admin admin = new Admin();
                BeanUtils.copyProperties(adminAddDto, admin);
                admin.setUserType(UserType.ADMIN.getCode());
                admin.setIsActive(adminAddDto.isActive());
                admin.setCreateTime(createTime);
                //设置认证ID
                admin.setAuthId(addUser.getData().getId());
                this.adminRepository.save(admin);

                return true;
            }
        } catch (Exception e) {
            log.error("create admin error", e);
        }
        return false;
    }

    @Transactional
    public boolean delete(int id) {
        try {
            //1.先获取该用户的认证ID
            Integer authId = this.adminRepository.findAuthIdById(id);
            if (authId != null) {
                ResponseDto<Boolean> user = this.authUserClient.deleteUser(authId);
                if (user != null && user.getData()) {
                    //2.删除本服务数据
                    this.adminRepository.deleteById(id);

                    return true;
                }
            }
        } catch (Exception e) {
            log.error("delete admin error", e);
        }
        return false;
    }

    @Transactional
    public boolean modifyAdminStatus(int id, boolean active) {
        try {
            //1.先获取该用户的认证ID
            Integer authId = this.adminRepository.findAuthIdById(id);
            if (authId != null) {
                ResponseDto<Boolean> user = this.authUserClient.activeUser(authId, active);
                if (user != null && user.getData()) {
                    //2.更新本服务数据
                    this.adminRepository.updateAdminActiveById(id, active);

                    return true;
                }
            }
        } catch (Exception e) {
            log.error("modifyAdminStatus admin error", e);
        }
        return false;
    }
}
