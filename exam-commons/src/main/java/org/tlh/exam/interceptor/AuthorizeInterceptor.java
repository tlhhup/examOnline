package org.tlh.exam.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.tlh.exam.auth.feign.AuthClient;
import org.tlh.exam.auth.model.resp.ResponseDto;
import org.tlh.exam.auth.model.resp.UserInfoRespDto;
import org.tlh.exam.holder.LoginUserHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/29
 * <p>
 * Github: https://github.com/tlhhup
 */
public class AuthorizeInterceptor implements HandlerInterceptor {

    private static final String AUTH = "X-Auth-Token";


    private AuthClient authClient;

    public void setAuthClient(AuthClient authClient) {
        this.authClient = authClient;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //校验token
        String token = request.getHeader(AUTH);
        //解析登陆用户信息
        ResponseDto<UserInfoRespDto> loginUser = this.authClient.getUserInfo(token);
        if(loginUser.getData()!=null)
            LoginUserHolder.setLoginUser(loginUser.getData());
        // todo 该处处理权限问题
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        //清空
        LoginUserHolder.resetLoginUser();
    }

}
