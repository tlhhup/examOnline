package org.tlh.exam.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tlh.exam.auth.model.RoleReqDto;
import org.tlh.exam.auth.model.RoleRespDto;
import org.tlh.exam.auth.service.RoleService;

import java.util.List;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/23
 * <p>
 * Github: https://github.com/tlhhup
 */
@RestController
@RequestMapping("/authRole")
@Api(value = "角色管理")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    @ApiOperation(value = "添加角色")
    public ResponseEntity<Boolean> addRole(@RequestBody RoleReqDto roleReqDto){
        boolean flag = this.roleService.createRole(roleReqDto);
        return ResponseEntity.ok(flag);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除角色")
    public ResponseEntity<Boolean> deleteRole(@PathVariable("id")int id){
        boolean flag = this.roleService.deleteRole(id);
        return ResponseEntity.ok(flag);
    }

    @PutMapping("/modify/{id}")
    @ApiOperation(value = "修改角色")
    public ResponseEntity<Boolean> updateRole(@PathVariable("id")int id,@RequestBody RoleReqDto roleReqDto){
        boolean flag = this.roleService.updateRole(id, roleReqDto);
        return ResponseEntity.ok(flag);
    }

    @GetMapping("/list")
    @ApiOperation(value = "角色列表")
    public List<RoleRespDto> findAll(){
        return this.roleService.findAll();
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "角色详情")
    public RoleRespDto findRoleDetail(int id){
        return this.roleService.findRoleDetail(id);
    }

}
