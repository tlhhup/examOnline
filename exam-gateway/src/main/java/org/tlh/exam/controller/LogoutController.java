package org.tlh.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.tlh.exam.auth.feign.AuthClient;

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
    public String logout(@RequestHeader("X-Auth-Token")String token){
        this.authClient.invalidate(token);
        return "success";
    }

}
