package org.tlh.exam.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tlh.exam.auth.model.req.JwtAuthenticationRequest;
import org.tlh.exam.auth.model.resp.JwtAuthenticationResponse;
import org.tlh.exam.auth.service.AuthService;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/28
 * <p>
 * Github: https://github.com/tlhhup
 */
@RestController
@Api(value = "用户认证")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登陆")
    public ResponseEntity<JwtAuthenticationResponse> createAuthenticationToken(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest){
        JwtAuthenticationResponse token=this.authService.authentication(jwtAuthenticationRequest);
        return ResponseEntity.ok(token);
    }

}
