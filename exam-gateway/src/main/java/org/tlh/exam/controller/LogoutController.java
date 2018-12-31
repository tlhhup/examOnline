package org.tlh.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.tlh.exam.commons.GatewayConstants;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/15
 * <p>
 * Github: https://github.com/tlhhup
 */
@RestController
public class LogoutController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/logout")
    public String logout(@RequestHeader("X-Auth-Token")String token){
        // todo 校验token是否合法，清除redis中缓存的token
        this.redisTemplate.opsForSet().remove(GatewayConstants.GATEWAY_TOKEN,token);
        return "success";
    }

}
