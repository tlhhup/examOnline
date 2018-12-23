package org.tlh.exam.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.tlh.exam.auth.fallback.AuthClientFallback;
import org.tlh.exam.auth.model.req.JwtAuthenticationRequest;
import org.tlh.exam.auth.model.resp.JwtAuthenticationResponse;
import org.tlh.exam.auth.model.resp.UserInfoRespDto;

import javax.validation.Valid;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/23
 * <p>
 * Github: https://github.com/tlhhup
 */
@FeignClient(name = "exam-auth-server",path = "/auth",fallback = AuthClientFallback.class)
public interface AuthClient {

    /**
     * 登陆认证
     * @param jwtAuthenticationRequest
     * @return
     */
    @PostMapping("/login")
    ResponseEntity<JwtAuthenticationResponse> createAuthenticationToken(
            @Valid @RequestBody JwtAuthenticationRequest jwtAuthenticationRequest);

    /**
     * token校验
     * @param token
     * @return
     */
    @PostMapping("/validation")
    ResponseEntity<Boolean> validationToken(@RequestHeader("X-Auth-Token")String token);

    /**
     * 刷新token
     * @param oldToken
     * @return
     */
    @PostMapping("/refresh")
    ResponseEntity<JwtAuthenticationResponse> refreshToken(@RequestHeader("X-Auth-Token")String oldToken);

    /**
     * token失效，登出
     * @param token
     * @return
     */
    @Deprecated
    @PostMapping("/invalidate")
    ResponseEntity<Boolean> invalidate(@RequestHeader("X-Auth-Token")String token);

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @GetMapping("/user/info")
    ResponseEntity<UserInfoRespDto> getUserInfo(@RequestHeader("X-Auth-Token")String token);

}
