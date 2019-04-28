package org.tlh.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by 离歌笑tlh/hu ping on 2019/4/28
 * <p>
 * Github: https://github.com/tlhhup
 */
@SpringBootApplication
@EnableTransactionManagement
public class ExamQuestionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamQuestionApplication.class,args);
    }

}
