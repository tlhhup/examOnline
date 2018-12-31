package org.tlh.exam.auth.fallback;

import org.springframework.stereotype.Component;
import org.tlh.exam.auth.feign.AuthClient;
import org.tlh.exam.auth.model.req.JwtAuthenticationRequest;
import org.tlh.exam.auth.model.resp.JwtAuthenticationResponse;
import org.tlh.exam.auth.model.resp.ResponseDto;
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
    public ResponseDto<JwtAuthenticationResponse> createAuthenticationToken(
            @Valid JwtAuthenticationRequest jwtAuthenticationRequest) {
        return null;
    }

    @Override
    public ResponseDto<Boolean> validationToken(String token) {
        return null;
    }

    @Override
    public ResponseDto<JwtAuthenticationResponse> refreshToken(String oldToken) {
        return null;
    }

    @Override
    public ResponseDto<Boolean> invalidate(String token) {
        return null;
    }

    @Override
    public ResponseDto<UserInfoRespDto> getUserInfo(String token) {
        return null;
    }
}
