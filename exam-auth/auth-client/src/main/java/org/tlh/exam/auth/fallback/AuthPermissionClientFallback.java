package org.tlh.exam.auth.fallback;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.tlh.exam.auth.feign.AuthPermissionClient;
import org.tlh.exam.auth.model.req.PermissionReqDto;
import org.tlh.exam.auth.model.resp.PermissionRespDto;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/23
 * <p>
 * Github: https://github.com/tlhhup
 */
@Component
public class AuthPermissionClientFallback implements AuthPermissionClient{
    @Override
    public ResponseEntity<Boolean> createPermission(PermissionReqDto permissionReqDto) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> deletePermission(int id) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> permissionActive(int id, boolean active) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> permissionUpdate(int id, PermissionReqDto permissionReqDto) {
        return null;
    }

    @Override
    public PermissionRespDto permissionDetail(int id) {
        return null;
    }
}
