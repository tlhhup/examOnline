package org.tlh.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@EnableEurekaClient
@SpringBootApplication
public class ExamGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamGatewayApplication.class,args);
    }

}
