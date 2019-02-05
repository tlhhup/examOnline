package org.tlh.exam.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/5
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "exam.user")
public class RefreshProps {

    private String name;

}
