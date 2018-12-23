package org.tlh.exam.auth;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tlh.exam.auth.config.DateFormatRegister;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/23
 * <p>
 * Github: https://github.com/tlhhup
 */
@Configuration
@EnableFeignClients
public class AuthClientAutoConfiguration {

    @Bean
    public FeignFormatterRegistrar dateFormatRegister(){
        return new DateFormatRegister();
    }

}
