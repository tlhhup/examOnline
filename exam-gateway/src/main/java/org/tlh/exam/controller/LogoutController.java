package org.tlh.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.tlh.exam.auth.feign.AuthClient;
import org.tlh.exam.commons.GatewayConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/15
 * <p>
 * Github: https://github.com/tlhhup
 */
@RestController
public class LogoutController {

    @Autowired
    private AuthClient authClient;

    @PostMapping("/logout")
    public Map<String, Object> logout(@RequestHeader("X-Auth-Token")String token){
        Map<String, Object> map = new HashMap<>();
        try {
            this.authClient.invalidate(token);
            map.put("code", 200);
            map.put("message", "success");
        } catch (Exception e) {
            map.put("code", GatewayConstants.LOGOUT_ERROR);
            map.put("message", e.getMessage());
        }
        map.put("data", null);
        return map;
    }

}
