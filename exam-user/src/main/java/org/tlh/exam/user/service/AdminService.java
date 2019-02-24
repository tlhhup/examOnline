package org.tlh.exam.user.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.auth.feign.AuthUserClient;
import org.tlh.exam.auth.model.req.UserAddDto;
import org.tlh.exam.auth.model.resp.ResponseDto;
import org.tlh.exam.auth.model.resp.UserRespDto;
import org.tlh.exam.user.entity.Admin;
import org.tlh.exam.user.model.resp.AdminRespDto;
import org.tlh.exam.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/6
 * <p>
 * Github: https://github.com/tlhhup
 */
@Service
@Transactional(readOnly = true)
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthUserClient authUserClient;

    public boolean createUser(){
        UserAddDto userAddDto = new UserAddDto();
        ResponseDto<UserRespDto> userRespDtoResponseDto = this.authUserClient.addUser(userAddDto);
        if(userRespDtoResponseDto!=null&&userRespDtoResponseDto.getData()!=null){
            UserRespDto user = userRespDtoResponseDto.getData();
            if(user.getId()!=null){
                return true;
            }
        }
        return false;
    }

    public Page<AdminRespDto> list(String userName, Pageable pageable){
        Page<Admin> page = this.userRepository.findAll(pageable);
        List<AdminRespDto> admins = page.stream().map(user -> {
            AdminRespDto admin = new AdminRespDto();
            BeanUtils.copyProperties(user,admin);

            ResponseDto<UserRespDto> userDetail = this.authUserClient.getUserDetail(user.getAuthId());
            if(userDetail!=null&&userDetail.getData()!=null){
                admin.setUserName(userDetail.getData().getUserName());
            }
            return admin;
        }).collect(Collectors.toList());
        return new PageImpl<>(admins, pageable, page.getTotalElements());
    }

}
