package org.tlh.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.tlh.exam.auth.feign.AuthClient;
import org.tlh.exam.interceptor.AuthorizeInterceptor;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@Configuration
public class AuthorizationAutoConfiguration implements WebMvcConfigurer {

    @Autowired
    private AuthClient authClient;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        AuthorizeInterceptor authorizeInterceptor=new AuthorizeInterceptor();
        authorizeInterceptor.setAuthClient(authClient);
        registry.addInterceptor(authorizeInterceptor).addPathPatterns("/**");
    }
}
