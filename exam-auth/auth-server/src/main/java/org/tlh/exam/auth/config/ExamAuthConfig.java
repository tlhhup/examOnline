package org.tlh.exam.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.tlh.exam.auth.interceptor.AuthorizeInterceptor;
import org.tlh.exam.auth.service.AuthService;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/22
 * <p>
 * Github: https://github.com/tlhhup
 */
@Configuration
public class ExamAuthConfig {

    @Autowired
    private ExamAuthServerProperties examAuthServerProperties;

    @Bean
    public Pbkdf2PasswordEncoder passwordEncoder(){
        Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(examAuthServerProperties.getSecret());
        passwordEncoder.setAlgorithm(Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA1);
        return passwordEncoder;
    }

    @Bean
    public AuthorizeInterceptor authorizeInterceptor(AuthService authService){
        AuthorizeInterceptor authorizeInterceptor=new AuthorizeInterceptor();
        authorizeInterceptor.setAuthService(authService);
        authorizeInterceptor.setExamAuthServerProperties(examAuthServerProperties);
        return authorizeInterceptor;
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        //配置
        RedisCacheConfiguration cacheConfiguration=RedisCacheConfiguration.defaultCacheConfig();
        cacheConfiguration=cacheConfiguration.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        cacheConfiguration=cacheConfiguration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return cacheConfiguration;
    }

    //@Configuration
    public class WebMvcConfig implements WebMvcConfigurer{

        @Autowired
        private AuthorizeInterceptor authorizeInterceptor;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(authorizeInterceptor).addPathPatterns("/*")//
                    .excludePathPatterns("/swagger-resources/**")//
                    .excludePathPatterns("/swagger-ui.html");
        }
    }

}
