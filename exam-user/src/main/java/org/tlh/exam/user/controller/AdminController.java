package org.tlh.exam.user.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.tlh.exam.auth.model.resp.PageInfo;
import org.tlh.exam.auth.model.resp.ResponseDto;
import org.tlh.exam.user.model.req.AdminAddDto;
import org.tlh.exam.user.model.req.AdminReqDto;
import org.tlh.exam.user.model.resp.AdminRespDto;
import org.tlh.exam.user.service.AdminService;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/5
 * <p>
 * Github: https://github.com/tlhhup
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/list")
    @ApiOperation(value = "管理员列表")
    public ResponseDto list(Pageable pageable,
                            @RequestParam(name = "userName", required = false) String userName) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
        Page<AdminRespDto> page = this.adminService.list(userName, pageRequest);
        PageInfo<AdminRespDto> pageInfo = new PageInfo<>(page.getContent(), page.getTotalElements());
        return ResponseDto.ok(pageInfo);
    }

    @PostMapping("/create")
    @ApiOperation(value = "添加管理员")
    public ResponseDto<Boolean> create(@RequestBody AdminAddDto adminAddDto) {
        boolean flag = this.adminService.create(adminAddDto);
        return ResponseDto.ok(flag);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除管理员")
    public ResponseDto<Boolean> delete(@PathVariable("id") int id) {
        boolean flag = this.adminService.delete(id);
        return ResponseDto.ok(flag);
    }

    @PutMapping("/modify/{id}")
    @ApiOperation(value = "修改管理员")
    public ResponseDto<Boolean> update(@PathVariable("id") int id, @RequestBody AdminReqDto adminReqDto) {
        boolean flag = this.adminService.update(id, adminReqDto);
        return ResponseDto.ok(flag);
    }

    @PutMapping("/modify/{id}/active/{active}")
    @ApiOperation(value = "激活(禁用)用户")
    public ResponseDto<Boolean> activeUser(@PathVariable("id")int id,@PathVariable("active") boolean active){
        boolean flag=this.adminService.modifyAdminStatus(id,active);
        return ResponseDto.ok(flag);
    }


}
