package org.tlh.exam.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.auth.entity.Permission;
import org.tlh.exam.auth.model.req.PermissionReqDto;
import org.tlh.exam.auth.model.resp.PermissionRespDto;
import org.tlh.exam.auth.repository.PermissionRepository;

import java.util.ArrayList;
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
    public boolean savePermission(PermissionReqDto permissionReqDto) {
        try {
            Permission permission = buildPermission(permissionReqDto);
            this.permissionRepository.save(permission);
            return true;
        } catch (Exception e) {
            log.error("create permission error", e.getMessage());
        }
        return false;
    }

    @Transactional
    public boolean deletePermission(int id) {
        try {
            //删除孩子节点
            this.deleteChildren(id);
            //删除自己
            this.permissionRepository.deleteRoleByPermissionId(id);
            this.permissionRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("delete permission error", e.getMessage());
        }
        return false;
    }

    //删除孩子节点
    private void deleteChildren(Integer parentId){
        List<Permission> menus = this.permissionRepository.findByParentId(parentId);
        if(menus!=null&&menus.size()>0){//判断是否是叶子菜单
            for(Permission menu:menus){
                deleteChildren(menu.getId());//递归删除孩子
            }
        }else{
            this.permissionRepository.deleteRoleByPermissionId(parentId);
            this.permissionRepository.deleteById(parentId);
        }
    }

    @Transactional
    public boolean changePermissionStatus(int id, boolean active) {
        return this.permissionRepository.updatePermissionStatus(id, active) > 0;
    }

    @Transactional
    public boolean updatePermission(int id, PermissionReqDto permissionReqDto) {
        try {
            Permission permission = buildPermission(permissionReqDto);
            permission.setId(id);
            this.permissionRepository.save(permission);
            return true;
        } catch (Exception e) {
            log.error("update permission error", e.getMessage());
        }
        return false;
    }

    public PermissionRespDto findPermissionDetail(int id) {
        Optional<Permission> permission = this.permissionRepository.findById(id);
        if (permission.isPresent()) {
            return buildPermissionDto(permission.get());
        }
        return null;
    }

    public List<PermissionRespDto> findAll(String name) {
        Permission model=new Permission();
        model.setName(name);
        ExampleMatcher matcher = ExampleMatcher.matching()//
                .withMatcher("name", match->match.startsWith());
        Example<Permission> example = Example.of(model, matcher);
        List<Permission> permissions = this.permissionRepository.findAll(example, Sort.by("sort"));
        List<PermissionRespDto> permissionRes = permissions.stream()//
                .map(permission -> buildPermissionDto(permission)).collect(Collectors.toList());
        return buildTreeMenu(permissionRes);
    }

    private Permission buildPermission(PermissionReqDto permissionReqDto) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionReqDto, permission);
        if (permissionReqDto.getParentId() != null) {
            Permission parent = new Permission();
            parent.setId(permissionReqDto.getParentId());
            permission.setParent(parent);
        }
        return permission;
    }

    private PermissionRespDto buildPermissionDto(Permission permission) {
        PermissionRespDto result = new PermissionRespDto();
        BeanUtils.copyProperties(permission, result);
        result.setLabel(result.getName());
        if (permission.getParent() != null) {
            result.setParentId(permission.getParent().getId());
        }
        return result;
    }

    private List<PermissionRespDto> buildTreeMenu(List<PermissionRespDto> menus){
        List<PermissionRespDto> trees = new ArrayList<>();

        for (PermissionRespDto menuDTO : menus) {
            //顶级菜单
            if (menuDTO.getParentId()==null) {
                trees.add(menuDTO);
            }
            //找该菜单的子菜单
            for (PermissionRespDto it : menus) {
                if (it.getParentId()!=null&&it.getParentId().equals(menuDTO.getId())) {
                    if (menuDTO.getChildren() == null) {
                        menuDTO.setChildren(new ArrayList<>());
                    }
                    menuDTO.getChildren().add(it);
                }
            }
        }
        return trees;
    }

}
