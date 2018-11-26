package org.tlh.exam.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.auth.entity.Permission;
import org.tlh.exam.auth.model.req.PermissionReqDto;
import org.tlh.exam.auth.model.resp.PermissionRespDto;
import org.tlh.exam.auth.repository.PermissionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/26
 * <p>
 * Github: https://github.com/tlhhup
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    public boolean savePermission(PermissionReqDto permissionReqDto){
        try {
            Permission permission = buildPermission(permissionReqDto);
            this.permissionRepository.save(permission);
            return true;
        }catch (Exception e){
            log.error("create permission error",e.getMessage());
        }
        return false;
    }

    @Transactional
    public boolean deletePermission(int id){
        try {
            this.permissionRepository.deleteById(id);
            return false;
        } catch (Exception e) {
            log.error("delete permission error",e.getMessage());
        }
        return false;
    }

    @Transactional
    public boolean changePermissionStatus(int id,boolean active){
        return this.permissionRepository.updatePermissionStatus(id, active)>0;
    }

    @Transactional
    public boolean updatePermission(int id,PermissionReqDto permissionReqDto){
        try {
            Permission permission = buildPermission(permissionReqDto);
            permission.setId(id);
            this.permissionRepository.save(permission);
            return true;
        }catch (Exception e){
            log.error("update permission error",e.getMessage());
        }
        return false;
    }

    public PermissionRespDto findPermissionDetail(int id){
        Optional<Permission> permission = this.permissionRepository.findById(id);
        if(permission.isPresent()){
            return buildPermissionDto(permission.get());
        }
        return null;
    }

    public Page<PermissionRespDto> findAll(Pageable pageable){
        Page<Permission> permissionPage = this.permissionRepository.findAll(pageable);
        List<PermissionRespDto> permissionRes = permissionPage.stream()//
                        .map(permission -> buildPermissionDto(permission)).collect(Collectors.toList());
        Page<PermissionRespDto> result=new PageImpl<>(permissionRes,pageable,permissionPage.getTotalElements());
        return result;
    }

    private Permission buildPermission(PermissionReqDto permissionReqDto){
        Permission permission=new Permission();
        BeanUtils.copyProperties(permissionReqDto,permission);
        if(permissionReqDto.getParentId()!=null){
            Permission parent=new Permission();
            parent.setId(permissionReqDto.getParentId());
            permission.setParent(parent);
        }
        return permission;
    }

    private PermissionRespDto buildPermissionDto(Permission permission){
        PermissionRespDto result=new PermissionRespDto();
        BeanUtils.copyProperties(permission,result);
        if (permission.getParent()!=null){
            result.setParentId(permission.getParent().getId());
        }
        return result;
    }

}
