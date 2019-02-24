package org.tlh.exam.user.interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.tlh.exam.user.holder.TokenHolder;

/**
 * 设置feign的请求头，添加token
 * Created by 离歌笑tlh/hu ping on 2019/2/24
 * <p>
 * Github: https://github.com/tlhhup
 */
@Slf4j
public class TokenSetInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String token = TokenHolder.getToken();
        if(StringUtils.hasText(token)){
            log.info("set the feign token");
            template.header(TokenStoreInterceptor.AUTH,token);
        }
    }
}
