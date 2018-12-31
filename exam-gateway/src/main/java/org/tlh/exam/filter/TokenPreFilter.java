package org.tlh.exam.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.tlh.exam.auth.feign.AuthClient;
import org.tlh.exam.auth.model.resp.ResponseDto;
import org.tlh.exam.commons.GatewayConstants;
import org.tlh.exam.config.ExamGatewayConfigProperties;
import org.tlh.exam.exceptions.IllegalRequestException;
import reactor.core.publisher.Mono;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@Slf4j
@Component
public class TokenPreFilter implements GlobalFilter, Ordered {

    @Autowired
    private ExamGatewayConfigProperties examGatewayConfigProperties;

    @Autowired
    private AuthClient authClient;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求的uri地址
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        //2.判断是否在忽略的路径中
        if (!this.examGatewayConfigProperties.getIgnorePaths().parallelStream().anyMatch(s -> path.startsWith(s))) {
            //3.获取token
            String token = request.getHeaders().getFirst("X-Auth-Token");
            if (StringUtils.hasText(token)) {
                //4.先查看登陆的token中是否存在
                Boolean member = this.redisTemplate.opsForSet().isMember(GatewayConstants.GATEWAY_TOKEN, token);
                if (member) {
                    ResponseDto<Boolean> data = this.authClient.validationToken(token);
                    if (data!=null&&data.getData()!=null&&!data.getData()) {
                        log.error("token validate error!");
                        this.redisTemplate.opsForSet().remove(GatewayConstants.GATEWAY_TOKEN, token);
                        throw new IllegalRequestException(data.getMessage(),GatewayConstants.RE_LOGIN);
                    }
                }else{
                    throw new IllegalRequestException("请重新登陆",GatewayConstants.RE_LOGIN);
                }
            } else {
                throw new IllegalRequestException("非法的请求头",GatewayConstants.ERROR_HEADER);
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        //对于pre类型，值越小越先执行
        return -2;
    }
}
