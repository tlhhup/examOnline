package org.tlh.exam.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tlh.exam.auth.config.ExamAuthServerProperties;
import org.tlh.exam.auth.model.req.JwtAuthenticationRequest;
import org.tlh.exam.auth.model.resp.JwtAuthenticationResponse;
import org.tlh.exam.auth.service.AuthService;
import org.tlh.exam.auth.util.jwt.IJWTInfo;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @Autowired
    private ExamAuthServerProperties examAuthServerProperties;

    @PostMapping("/login")
    @ApiOperation(value = "用户登陆")
    public ResponseEntity<JwtAuthenticationResponse> createAuthenticationToken(@Valid @RequestBody JwtAuthenticationRequest jwtAuthenticationRequest) {
        JwtAuthenticationResponse token = this.authService.authentication(jwtAuthenticationRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/validation")
    @ApiOperation(value = "校验token")
    public ResponseEntity<Boolean> validationToken(HttpServletRequest request) {
        String token = request.getHeader(this.examAuthServerProperties.getHeaderToken());
        IJWTInfo info = this.authService.validation(token);
        return ResponseEntity.ok(info != null);
    }

    @PostMapping("/refresh")
    @ApiOperation(value = "刷新token")
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(HttpServletRequest request) {
        String oldToken = request.getHeader(this.examAuthServerProperties.getHeaderToken());
        JwtAuthenticationResponse token = this.authService.refreshToken(oldToken);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/invalidate")
    public ResponseEntity<Boolean> invalidate(HttpServletRequest request){
        String token = request.getHeader(this.examAuthServerProperties.getHeaderToken());
        return ResponseEntity.ok(this.authService.invalidate(token));
    }

}
