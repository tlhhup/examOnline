package org.tlh.exam.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.tlh.exam.auth.model.resp.ResponseDto;
import org.tlh.exam.auth.model.req.RoleReqDto;
import org.tlh.exam.auth.model.resp.RoleRespDto;
import org.tlh.exam.auth.service.RoleService;

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
    public ResponseDto<Boolean> addRole(@RequestBody RoleReqDto roleReqDto){
        boolean flag = this.roleService.createRole(roleReqDto);
        return ResponseDto.ok(flag);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除角色")
    public ResponseDto<Boolean> deleteRole(@PathVariable("id")int id){
        boolean flag = this.roleService.deleteRole(id);
        return ResponseDto.ok(flag);
    }

    @PutMapping("/modify/{id}")
    @ApiOperation(value = "修改角色")
    public ResponseDto<Boolean> updateRole(@PathVariable("id")int id,@RequestBody RoleReqDto roleReqDto){
        boolean flag = this.roleService.updateRole(id, roleReqDto);
        return ResponseDto.ok(flag);
    }

    @GetMapping("/list")
    @ApiOperation(value = "角色列表")
    public Page<RoleRespDto> findAll(Pageable pageable){
        return this.roleService.findAll(pageable);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "角色详情")
    public ResponseDto<RoleRespDto> findRoleDetail(int id){
        return ResponseDto.ok(this.roleService.findRoleDetail(id));
    }

}
