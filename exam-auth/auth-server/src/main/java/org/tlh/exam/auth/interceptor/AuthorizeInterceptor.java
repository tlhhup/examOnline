package org.tlh.exam.auth.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.tlh.exam.auth.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/29
 * <p>
 * Github: https://github.com/tlhhup
 */
public class AuthorizeInterceptor implements HandlerInterceptor{

    private AuthService authService;

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        //认证的请求，放过
        if(uri.startsWith("/auth/")){
            return true;
        }
        //校验token
        String token = request.getHeader("token");
        //todo token校验
        return false;
    }

}
