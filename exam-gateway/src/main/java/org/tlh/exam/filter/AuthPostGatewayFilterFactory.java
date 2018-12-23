package org.tlh.exam.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * 为Post类型的filter
 * Created by 离歌笑tlh/hu ping on 2018/12/18
 * <p>
 * Github: https://github.com/tlhhup
 */
@Slf4j
@Component
public class AuthPostGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthPostGatewayFilterFactory.Config> {

    public AuthPostGatewayFilterFactory() {
        // 该构造函数必须创建，初始化配置类，默认为object
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                ServerHttpResponse response = exchange.getResponse();
                HttpHeaders headers = response.getHeaders();
                // todo 解析token的值 存入redis中
                String token=headers.getFirst("X-Auth-Token");
            }));
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("enabled");
    }

    @Data
    public static class Config {
        private boolean enabled;
    }

}
