package org.tlh.exam.auth.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.tlh.exam.auth.interceptor.AuthorizeInterceptor;
import org.tlh.exam.auth.interceptor.LocaleInterceptor;
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
        RedisCacheConfiguration config=RedisCacheConfiguration.defaultCacheConfig();
        config=config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        //value序列化
        Jackson2JsonRedisSerializer valueSerializer=new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper=new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        valueSerializer.setObjectMapper(mapper);
        config=config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer));
        return config;
    }

    @Configuration
    public class WebMvcConfig implements WebMvcConfigurer{

        @Autowired
        private AuthorizeInterceptor authorizeInterceptor;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            //1.权限过滤
            /*registry.addInterceptor(authorizeInterceptor).addPathPatterns("/*")//
                    .excludePathPatterns("/swagger-resources/**")//
                    .excludePathPatterns("/swagger-ui.html");*/
            //2.国际化
            registry.addInterceptor(new LocaleInterceptor()).addPathPatterns("/**");
        }
    }

}
