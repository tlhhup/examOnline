package org.tlh.exam.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/23
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@ConfigurationProperties(prefix = ExamGatewayConfigProperties.PREFIX)
public class ExamGatewayConfigProperties {

    public static final String PREFIX="exam.gateway";

    private List<String> ignorePaths;

}
