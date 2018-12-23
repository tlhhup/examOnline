package org.tlh.exam.auth.fallback;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.tlh.exam.auth.feign.AuthClient;
import org.tlh.exam.auth.model.req.JwtAuthenticationRequest;
import org.tlh.exam.auth.model.resp.JwtAuthenticationResponse;
import org.tlh.exam.auth.model.resp.UserInfoRespDto;

import javax.validation.Valid;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/23
 * <p>
 * Github: https://github.com/tlhhup
 */
@Component
public class AuthClientFallback implements AuthClient{

    @Override
    public ResponseEntity<JwtAuthenticationResponse> createAuthenticationToken(
            @Valid JwtAuthenticationRequest jwtAuthenticationRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> validationToken(String token) {
        return null;
    }

    @Override
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(String oldToken) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> invalidate(String token) {
        return null;
    }

    @Override
    public ResponseEntity<UserInfoRespDto> getUserInfo(String token) {
        return null;
    }
}
