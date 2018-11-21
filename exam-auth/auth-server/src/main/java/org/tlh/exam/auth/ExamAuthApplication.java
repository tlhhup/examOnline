package org.tlh.exam.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringCloudApplication
@EnableSpringDataWebSupport
@EnableTransactionManagement
public class ExamAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamAuthApplication.class,args);
    }

}
