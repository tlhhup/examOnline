package org.tlh.exam.auth.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.tlh.exam.auth.config.ExamAuthServerProperties;
import org.tlh.exam.auth.exception.JwtAuthException;
import org.tlh.exam.auth.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/29
 * <p>
 * Github: https://github.com/tlhhup
 */
public class AuthorizeInterceptor implements HandlerInterceptor{

    private static final String AUTH="X-Auth-Token";

    private AuthService authService;

    private ExamAuthServerProperties examAuthServerProperties;

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public void setExamAuthServerProperties(ExamAuthServerProperties examAuthServerProperties) {
        this.examAuthServerProperties = examAuthServerProperties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        //认证的请求，放过
        if(isIgnorePath(uri)){
            return true;
        }
        //校验token
        String token = request.getHeader(AUTH);
        if (StringUtils.isEmpty(token)){
            String message="The Request header:{%s} is mission!";
            throw new JwtAuthException(String.format(message,AUTH));
        }
        //todo 后续的权限校验该放在这里?
        return true;
    }

    private boolean isIgnorePath(String uri){
        if (uri.equals("/"))
            return true;
        return this.examAuthServerProperties.getIgnorePath().parallelStream().anyMatch((t)->t.matches(uri));
    }

}
