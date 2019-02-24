package org.tlh.exam.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.tlh.exam.user.interceptors.TokenSetInterceptor;
import org.tlh.exam.user.interceptors.TokenStoreInterceptor;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/24
 * <p>
 * Github: https://github.com/tlhhup
 */
@Configuration
public class ExamUserConfig {

    @Configuration
    public class WebMvcConfig implements WebMvcConfigurer{

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new TokenStoreInterceptor()).addPathPatterns("/**");
        }
    }

    @Configuration
    public class FeignConfig{

        @Bean
        public TokenSetInterceptor tokenSetInterceptor(){
            return new TokenSetInterceptor();
        }

    }

}
