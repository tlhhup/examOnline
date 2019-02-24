package org.tlh.exam.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.tlh.exam.auth.EnableAuthClient;
import org.tlh.exam.user.config.ExamUserConfig;
import org.tlh.exam.user.config.RefreshProps;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/5
 * <p>
 * Github: https://github.com/tlhhup
 */
@EnableAuthClient
@EnableEurekaClient
@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients(defaultConfiguration = ExamUserConfig.FeignConfig.class)
@EnableConfigurationProperties(RefreshProps.class)
public class ExamUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamUserApplication.class,args);
    }

}
