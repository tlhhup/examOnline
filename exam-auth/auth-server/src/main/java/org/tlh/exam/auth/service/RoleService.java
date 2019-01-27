package org.tlh.exam.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.auth.entity.Permission;
import org.tlh.exam.auth.entity.Role;
import org.tlh.exam.auth.holder.JwtInfoHolder;
import org.tlh.exam.auth.model.req.RoleReqDto;
import org.tlh.exam.auth.model.resp.RoleRespDto;
import org.tlh.exam.auth.repository.RoleRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/23
 * <p>
 * Github: https://github.com/tlhhup
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public boolean createRole(RoleReqDto roleReqDto) {
        try {
            this.roleRepository.save(roleDto2Role(roleReqDto));
            return true;
        } catch (Exception e) {
            log.error("add role error", e.getMessage());
        }
        return false;
    }

    @Transactional
    public boolean deleteRole(int id) {
        try {
            this.roleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("delete role error", e.getMessage());
        }
        return false;
    }

    @Transactional
    public boolean updateRole(int id, RoleReqDto roleReqDto) {
        try {
            Role role = roleDto2Role(roleReqDto);
            role.setId(id);
            this.roleRepository.save(role);
            return true;
        } catch (Exception e) {
            log.error("update role error", e.getMessage());
        }
        return false;
    }

    public Page<RoleRespDto> findAll(String roleName, String creator, Pageable pageable) {
        Role query = new Role();
        query.setRoleName(roleName);
        query.setCreator(creator);
        ExampleMatcher matcher = ExampleMatcher.matching()//
                .withMatcher("roleName", match->match.startsWith())
                .withMatcher("creator", matcher1 -> matcher1.storeDefaultMatching());
        Example<Role> example = Example.of(query, matcher);
        Page<Role> rolePage = this.roleRepository.findAll(example,pageable);
        List<RoleRespDto> collect = rolePage.stream().map(role -> role2RoleResp(role)).collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, rolePage.getTotalElements());
    }

    public List<RoleRespDto> findAll(){
        List<Role> roles = this.roleRepository.findAll();
        List<RoleRespDto> collect = roles.parallelStream().map(role -> role2RoleResp(role)).collect(Collectors.toList());
        return collect;
    }

    public RoleRespDto findRoleDetail(int id) {
        Optional<Role> role = this.roleRepository.findById(id);
        if (!role.isPresent()) {
            return null;
        }
        return role2RoleResp(role.get());
    }

    private Role roleDto2Role(RoleReqDto roleReqDto) {
        Role role = new Role();
        role.setRoleName(roleReqDto.getRoleName());
        role.setRoleValue(roleReqDto.getRoleValue());
        role.setDescription(roleReqDto.getDescription());
        role.setIsActive(roleReqDto.getIsActive());
        role.setCreateTime(roleReqDto.getCreateTime());
        role.setCreator(JwtInfoHolder.getIJWTInfo().getName());

        //分配权限
        Integer[] permissionIds = roleReqDto.getPermissionIds();
        if(permissionIds!=null&&permissionIds.length!=0){
            List<Permission> permissions = Arrays.stream(permissionIds).map(id -> {
                Permission permission = new Permission();
                permission.setId(id);
                return permission;
            }).collect(Collectors.toList());
            role.setPermissions(permissions);
        }
        return role;
    }

    private RoleRespDto role2RoleResp(Role role) {
        RoleRespDto roleRespDto = new RoleRespDto();
        roleRespDto.setId(role.getId());
        roleRespDto.setRoleName(role.getRoleName());
        roleRespDto.setRoleValue(role.getRoleValue());
        roleRespDto.setDescription(role.getDescription());
        roleRespDto.setIsActive(role.getIsActive());
        roleRespDto.setCreator(role.getCreator());
        roleRespDto.setCreateTime(role.getCreateTime());
        return roleRespDto;
    }

}
