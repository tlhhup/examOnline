package org.tlh.exam.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.tlh.exam.auth.model.resp.ResponseDto;
import org.tlh.exam.auth.model.req.PermissionReqDto;
import org.tlh.exam.auth.model.resp.PermissionRespDto;
import org.tlh.exam.auth.service.PermissionService;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/26
 * <p>
 * Github: https://github.com/tlhhup
 */
@RestController
@Api(value = "权限管理")
@RequestMapping(value = "/authPermission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/create")
    @ApiOperation(value = "添加权限")
    public ResponseDto<Boolean> createPermission(@RequestBody PermissionReqDto permissionReqDto){
        boolean flag = this.permissionService.savePermission(permissionReqDto);
        return ResponseDto.ok(flag);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除权限")
    public ResponseDto<Boolean> deletePermission(@PathVariable("id") int id){
        boolean flag = this.permissionService.deletePermission(id);
        return ResponseDto.ok(flag);
    }

    @ApiIgnore(value = "暂不考虑")
    @PostMapping("/modify/{id}/active")
    @ApiOperation(value = "禁用(启用)权限")
    public ResponseDto<Boolean> permissionActive(@PathVariable("id") int id,@RequestParam("active") boolean active){
        boolean flag = this.permissionService.changePermissionStatus(id, active);
        return ResponseDto.ok(flag);
    }

    @PostMapping("/modify/{id}")
    @ApiOperation(value = "更新权限")
    public ResponseDto<Boolean> permissionUpdate(@PathVariable("id") int id,@RequestBody PermissionReqDto permissionReqDto){
        boolean flag = this.permissionService.updatePermission(id, permissionReqDto);
        return ResponseDto.ok(flag);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "权限详情")
    public ResponseDto<PermissionRespDto> permissionDetail(@PathVariable("id") int id){
        return ResponseDto.ok(this.permissionService.findPermissionDetail(id));
    }

    @GetMapping("/list")
    @ApiOperation(value = "权限列表")
    public Page<PermissionRespDto> permissionAll(Pageable pageable){
        return this.permissionService.findAll(pageable);
    }

}
