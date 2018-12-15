package org.tlh.exam.auth;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.tlh.exam.auth.config.ExamAuthServerProperties;

@EnableCaching
@EnableSwagger2Doc
@SpringCloudApplication
@EnableSpringDataWebSupport
@EnableTransactionManagement
@EnableConfigurationProperties(ExamAuthServerProperties.class)
public class ExamAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamAuthApplication.class,args);
    }

}
