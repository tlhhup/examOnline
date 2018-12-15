package org.tlh.exam.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/15
 * <p>
 * Github: https://github.com/tlhhup
 */
@RestController
public class LogoutController {

    @PostMapping("/logout")
    public String logout(){
        // todo 校验token是否合法，清除redis中缓存的token
        return "success";
    }

}
