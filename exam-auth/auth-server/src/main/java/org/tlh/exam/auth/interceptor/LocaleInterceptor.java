package org.tlh.exam.auth.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.tlh.exam.auth.holder.LocaleHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/31
 * <p>
 * Github: https://github.com/tlhhup
 */
public class LocaleInterceptor implements HandlerInterceptor {

    private static final String LANGUAGE = "X-Language";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取请求头中的语言
        String language = request.getHeader(LANGUAGE);
        if (StringUtils.hasText(language)) {
            switch (language) {
                case "en_US":
                    LocaleHolder.setLocale(Locale.US);
                    break;
                default:
                    LocaleHolder.setLocale(Locale.CHINESE);
                    break;
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        LocaleHolder.resetLocal();
    }
}
