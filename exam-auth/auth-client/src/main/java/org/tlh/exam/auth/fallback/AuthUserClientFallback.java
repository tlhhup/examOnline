package org.tlh.exam.auth.fallback;

import org.springframework.stereotype.Component;
import org.tlh.exam.auth.feign.AuthUserClient;
import org.tlh.exam.auth.model.req.UserAddDto;
import org.tlh.exam.auth.model.resp.ResponseDto;
import org.tlh.exam.auth.model.resp.UserRespDto;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/23
 * <p>
 * Github: https://github.com/tlhhup
 */
@Component
public class AuthUserClientFallback implements AuthUserClient{
    @Override
    public ResponseDto<UserRespDto> addUser(UserAddDto userAddDto) {
        return null;
    }

    @Override
    public ResponseDto<Boolean> deleteUser(int id) {
        return null;
    }

    @Override
    public ResponseDto<Boolean> modifyPassword(int id, String password) {
        return null;
    }

    @Override
    public ResponseDto<Boolean> activeUser(int id, boolean active) {
        return null;
    }

    @Override
    public ResponseDto<UserRespDto> getUserDetail(int id) {
        return null;
    }
}
