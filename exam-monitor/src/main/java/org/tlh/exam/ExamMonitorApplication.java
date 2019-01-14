package org.tlh.exam;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by 离歌笑tlh/hu ping on 2019/1/14
 * <p>
 * Github: https://github.com/tlhhup
 */
@EnableEurekaClient
@EnableAdminServer
@SpringBootApplication
public class ExamMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamMonitorApplication.class,args);
    }

}
