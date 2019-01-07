package org.tlh.exam.auth.service;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.tlh.exam.auth.config.ExamAuthServerProperties;
import org.tlh.exam.auth.constatns.CommonConstants;
import org.tlh.exam.auth.entity.User;
import org.tlh.exam.auth.exception.JwtAuthException;
import org.tlh.exam.auth.model.req.JwtAuthenticationRequest;
import org.tlh.exam.auth.model.resp.JwtAuthenticationResponse;
import org.tlh.exam.auth.model.resp.UserInfoRespDto;
import org.tlh.exam.auth.repository.UserRepository;
import org.tlh.exam.auth.util.JwtTokenUtil;
import org.tlh.exam.auth.util.LocaleMessageResource;
import org.tlh.exam.auth.util.jwt.IJWTInfo;
import org.tlh.exam.auth.util.jwt.JWTInfo;

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/28
 * <p>
 * Github: https://github.com/tlhhup
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Pbkdf2PasswordEncoder passwordEncoder;

    @Autowired
    private LocaleMessageResource messageResource;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ExamAuthServerProperties examAuthServerProperties;

    private StringRedisTemplate redisTemplate;
    private StringRedisSerializer stringSerializer;

    @Autowired
    public AuthService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringSerializer=(StringRedisSerializer)redisTemplate.getKeySerializer();
    }

    public JwtAuthenticationResponse authentication(JwtAuthenticationRequest jwtAuthenticationRequest) {
        Optional<User> one = this.userRepository.findOneByUserNameAndUserType(jwtAuthenticationRequest.getUserName(),jwtAuthenticationRequest.getUserType().getCode());
        //用户不存在
        if (!one.isPresent()||!this.passwordEncoder.matches(jwtAuthenticationRequest.getPassword(),one.get().getPassword())){
            throw new JwtAuthException(this.messageResource.getMessage("auth.user.not.found"));
        }
        //用户被禁用
        User user=one.get();
        if (!user.getIsActive()){
            throw new JwtAuthException(this.messageResource.getMessage("auth.user.locked"));
        }
        //生成token
        try {
            String token = jwtTokenUtil.generateToken(new JWTInfo(user.getUserName(), user.getId(), user.getUserName()));
            if(StringUtils.isEmpty(token)){
                throw new JwtAuthException(this.messageResource.getMessage("auth.token.create.error"));
            }
            //模仿oauth2的redisTokenStore的方式将token存入redis
            String key = this.generateKey(token);
            this.redisTemplate.opsForValue().set(key,token);
            this.redisTemplate.expire(key,examAuthServerProperties.getJwtExpire(), TimeUnit.SECONDS);
            return new JwtAuthenticationResponse(token);
        } catch (Exception e) {
            throw new JwtAuthException(this.messageResource.getMessage("auth.token.create.error"));
        }
    }

    public IJWTInfo validation(String token){
        try {
            String key = generateKey(token);
            if(!this.redisTemplate.hasKey(key)){
                throw new JwtAuthException(this.messageResource.getMessage("auth.token.validate.expired"));
            }
            IJWTInfo info = this.jwtTokenUtil.getInfoFromToken(token);
            return info;
        } catch (ExpiredJwtException e){
            throw new JwtAuthException(this.messageResource.getMessage("auth.token.validate.expired"));
        }catch (Exception e) {
            throw new JwtAuthException(this.messageResource.getMessage("auth.token.validate.error"));
        }
    }

    public UserInfoRespDto getUserInfo(String token) {
        IJWTInfo info = this.validation(token);
        return this.userService.findUserInfoById(info.getId());
    }

    public JwtAuthenticationResponse refreshToken(String oldToken) {
        //1.先校验之前的token是否有效
        IJWTInfo info = this.validation(oldToken);
        try {
            //2.将旧的token设置是失效
            this.invalidate(oldToken);
            //3.生成新的token
            String token = jwtTokenUtil.generateToken(new JWTInfo(info.getUniqueName(), info.getId(), info.getName()));
            if(StringUtils.isEmpty(token)){
                throw new JwtAuthException(this.messageResource.getMessage("auth.token.refresh"));
            }
            //模仿oauth2的redisTokenStore的方式将token存入redis
            String key = this.generateKey(token);
            this.redisTemplate.opsForValue().set(key,token);
            this.redisTemplate.expire(key,examAuthServerProperties.getJwtExpire(), TimeUnit.SECONDS);
            return new JwtAuthenticationResponse(token);
        } catch (Exception e) {
            throw new JwtAuthException(this.messageResource.getMessage("auth.token.refresh"));
        }
    }

    public boolean invalidate(String token){
        //删除之前的token
        String key = generateKey(token);
        return this.redisTemplate.delete(key);
    }


    private String generateKey(String token){
        String key= CommonConstants.TOKEN_KEY_PREFIX.concat(token);
        //todo 该key处理简单，目前和token一致，只多了auth前缀
        byte[] serialize = this.stringSerializer.serialize(key);
        return new String(serialize, Charset.defaultCharset());
    }

}
