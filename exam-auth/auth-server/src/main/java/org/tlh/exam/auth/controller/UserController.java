package org.tlh.exam.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tlh.exam.auth.model.req.UserAddDto;
import org.tlh.exam.auth.model.resp.UserRespDto;
import org.tlh.exam.auth.service.UserService;

import java.util.List;

@Api
@RestController
@RequestMapping("/authUser")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @ApiOperation("添加用户")
    public ResponseEntity<Boolean> addUser(@RequestBody UserAddDto userAddDto) {
        boolean flag = this.userService.saveUser(userAddDto);
        return ResponseEntity.ok(flag);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除用户")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") int id) {
        boolean flag = this.userService.deleteUser(id);
        return ResponseEntity.ok(flag);
    }

    @PutMapping("/modify/{id}/pwd")
    @ApiOperation(value = "修改密码")
    public ResponseEntity<Boolean> modifyPassword(@PathVariable("id") int id, @RequestParam("password") String password) {
        boolean flag = this.userService.modifyPassword(id, password);
        return ResponseEntity.ok(flag);
    }

    @PutMapping("/modify/{id}/active")
    @ApiOperation(value = "激活(禁用)用户")
    public ResponseEntity<Boolean> activeUser(@PathVariable("id")int id,@RequestParam("active") boolean active){
        boolean flag=this.userService.modifyUserStatus(id,active);
        return ResponseEntity.ok(flag);
    }

    @GetMapping("/list")
    @ApiOperation(value = "用户列表")
    public Page<UserRespDto> findUserInfo(Pageable pageable){
        return this.userService.findAll(pageable);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "用户详情")
    public UserRespDto getUserDetail(@PathVariable("id") int id){
        return this.userService.findUserDetail(id);
    }

}
