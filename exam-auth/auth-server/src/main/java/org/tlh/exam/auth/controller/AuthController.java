package org.tlh.exam.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tlh.exam.auth.model.resp.ResponseDto;
import org.tlh.exam.auth.model.req.JwtAuthenticationRequest;
import org.tlh.exam.auth.model.resp.JwtAuthenticationResponse;
import org.tlh.exam.auth.model.resp.UserInfoRespDto;
import org.tlh.exam.auth.service.AuthService;
import org.tlh.exam.auth.util.jwt.IJWTInfo;

import javax.servlet.http.HttpServletResponse;
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

    @PostMapping("/login")
    @ApiOperation(value = "用户登陆")
    public ResponseDto<JwtAuthenticationResponse> createAuthenticationToken(
            @Valid @RequestBody JwtAuthenticationRequest jwtAuthenticationRequest) {
        JwtAuthenticationResponse token = this.authService.authentication(jwtAuthenticationRequest);
        //设置响应头
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setHeader("X-Auth-Token", token.getToken());
        return ResponseDto.ok(token);
    }

    @PostMapping("/validation")
    @ApiOperation(value = "校验token")
    public ResponseDto<Boolean> validationToken(@RequestHeader("X-Auth-Token") String token) {
        IJWTInfo info = this.authService.validation(token);
        return ResponseDto.ok(info != null);
    }

    @PostMapping("/refresh")
    @ApiOperation(value = "刷新token")
    public ResponseDto<JwtAuthenticationResponse> refreshToken(@RequestHeader("X-Auth-Token") String oldToken) {
        JwtAuthenticationResponse token = this.authService.refreshToken(oldToken);
        return ResponseDto.ok(token);
    }

    @PostMapping("/invalidate")
    public ResponseDto<Boolean> invalidate(@RequestHeader("X-Auth-Token") String token) {
        return ResponseDto.ok(this.authService.invalidate(token));
    }

    @GetMapping("/user/info")
    public ResponseDto<UserInfoRespDto> getUserInfo(@RequestHeader("X-Auth-Token") String token) {
        return ResponseDto.ok(this.authService.getUserInfo(token));
    }

}
