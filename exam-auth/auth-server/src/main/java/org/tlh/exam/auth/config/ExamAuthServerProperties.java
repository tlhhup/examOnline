package org.tlh.exam.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/22
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@ConfigurationProperties(prefix = "exam.auth")
public class ExamAuthServerProperties {

    private String secret;

}
