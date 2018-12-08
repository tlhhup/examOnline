package org.tlh.exam.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/1
 * <p>
 * Github: https://github.com/tlhhup
 */
public class TokenFilter implements GatewayFilter{

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // todo 处理登陆逻辑
        return null;
    }

}
