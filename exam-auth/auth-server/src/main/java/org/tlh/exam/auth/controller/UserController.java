package org.tlh.exam.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.tlh.exam.auth.model.req.ChangePwdReqDto;
import org.tlh.exam.auth.model.req.UserAddDto;
import org.tlh.exam.auth.model.resp.PageInfo;
import org.tlh.exam.auth.model.resp.ResponseDto;
import org.tlh.exam.auth.model.resp.UserRespDto;
import org.tlh.exam.auth.service.UserService;

import javax.validation.Valid;

@Api
@RestController
@RequestMapping("/authUser")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @ApiOperation("添加用户")
    public ResponseDto<Boolean> addUser(@RequestBody UserAddDto userAddDto) {
        boolean flag = this.userService.saveUser(userAddDto);
        return ResponseDto.ok(flag);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除用户")
    public ResponseDto<Boolean> deleteUser(@PathVariable("id") int id) {
        boolean flag = this.userService.deleteUser(id);
        return ResponseDto.ok(flag);
    }

    @PostMapping("/checkPassword")
    @ApiOperation(value = "密码校验")
    public ResponseDto<Boolean> checkPassword(@Valid @RequestBody ChangePwdReqDto changePwdReqDto) {
        boolean flag = this.userService.checkPassword(changePwdReqDto.getId(), changePwdReqDto.getPassword());
        return ResponseDto.ok(flag);
    }

    @PutMapping("/modify/{id}/pwd")
    @ApiOperation(value = "修改密码")
    public ResponseDto<Boolean> modifyPassword(@PathVariable("id") int id,
                                               @Valid @RequestBody ChangePwdReqDto changePwdReqDto) {
        boolean flag = this.userService.modifyPassword(id, changePwdReqDto.getPassword());
        return ResponseDto.ok(flag);
    }

    @PutMapping("/modify/{id}/resetPwd")
    @ApiOperation(value = "重制密码")
    public ResponseDto<Boolean> modifyPassword(@PathVariable("id") int id) {
        boolean flag = this.userService.resetPassword(id);
        return ResponseDto.ok(flag);
    }

    @PutMapping("/modify/{id}/active/{active}")
    @ApiOperation(value = "激活(禁用)用户")
    public ResponseDto<Boolean> activeUser(@PathVariable("id")int id,@PathVariable("active") boolean active){
        boolean flag=this.userService.modifyUserStatus(id,active);
        return ResponseDto.ok(flag);
    }

    @GetMapping("/list")
    @ApiOperation(value = "用户列表")
    public ResponseDto findUserInfo(Pageable pageable,
                                    @RequestParam(value = "userName",required = false) String userName,
                                    @RequestParam(value = "userType",required = false) Integer userType){
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
        Page<UserRespDto> page = this.userService.findAll(userName,userType,pageRequest);
        PageInfo<UserRespDto> pageInfo = new PageInfo<>(page.getContent(), page.getTotalElements());
        return ResponseDto.ok(pageInfo);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "用户详情")
    public ResponseDto<UserRespDto> getUserDetail(@PathVariable("id") int id){
        return ResponseDto.ok(this.userService.findUserDetail(id));
    }

}
