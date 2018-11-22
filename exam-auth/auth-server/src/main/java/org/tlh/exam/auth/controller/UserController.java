package org.tlh.exam.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tlh.exam.auth.model.UserResDto;
import org.tlh.exam.auth.service.UserService;

@Api
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @ApiOperation("添加用户")
    public ResponseEntity<Boolean> addUser(@RequestBody UserResDto userResDto){
        boolean flag=this.userService.saveUser(userResDto);
        return ResponseEntity.ok(flag);
    }

}
