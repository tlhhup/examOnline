package org.tlh.exam.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/22
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@ConfigurationProperties(prefix = "exam.auth")
public class ExamAuthServerProperties {

    /**
     * 密码加密密钥
     */
    private String secret;

    /**
     * jwt默认失效时间
     */
    private int jwtExpire = 30 * 60;

    /**
     * server端RSA密钥
     */
    private String serverRsaSecret;

    /**
     * client端RSA密钥
     */
    private String clientRsaSecret;

    /**
     * 无需登陆验证路径
     */
    private List<String> ignorePath;
}
