package org.tlh.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ExamAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamAdminApplication.class,args);
    }

}
