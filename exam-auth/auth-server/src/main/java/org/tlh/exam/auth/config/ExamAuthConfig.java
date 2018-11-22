package org.tlh.exam.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/22
 * <p>
 * Github: https://github.com/tlhhup
 */
@Configuration
public class ExamAuthConfig {

    @Autowired
    private ExamAuthServerProperties examAuthServerProperties;

    @Bean
    public Pbkdf2PasswordEncoder passwordEncoder(){
        Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(examAuthServerProperties.getSecret());
        passwordEncoder.setAlgorithm(Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA1);
        return passwordEncoder;
    }
}
