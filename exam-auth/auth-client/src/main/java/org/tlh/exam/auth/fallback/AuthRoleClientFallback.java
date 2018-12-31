package org.tlh.exam.auth.fallback;

import org.springframework.stereotype.Component;
import org.tlh.exam.auth.feign.AuthRoleClient;
import org.tlh.exam.auth.model.req.RoleReqDto;
import org.tlh.exam.auth.model.resp.ResponseDto;
import org.tlh.exam.auth.model.resp.RoleRespDto;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/23
 * <p>
 * Github: https://github.com/tlhhup
 */
@Component
public class AuthRoleClientFallback implements AuthRoleClient{
    @Override
    public ResponseDto<Boolean> addRole(RoleReqDto roleReqDto) {
        return null;
    }

    @Override
    public ResponseDto<Boolean> deleteRole(int id) {
        return null;
    }

    @Override
    public ResponseDto<Boolean> updateRole(int id, RoleReqDto roleReqDto) {
        return null;
    }

    @Override
    public ResponseDto<RoleRespDto> findRoleDetail(int id) {
        return null;
    }
}
