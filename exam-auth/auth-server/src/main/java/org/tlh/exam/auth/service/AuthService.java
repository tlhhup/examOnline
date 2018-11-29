package org.tlh.exam.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.auth.entity.User;
import org.tlh.exam.auth.exception.JwtAuthException;
import org.tlh.exam.auth.model.req.JwtAuthenticationRequest;
import org.tlh.exam.auth.model.resp.JwtAuthenticationResponse;
import org.tlh.exam.auth.repository.UserRepository;
import org.tlh.exam.auth.util.JwtTokenUtil;
import org.tlh.exam.auth.util.LocaleMessageResource;
import org.tlh.exam.auth.util.jwt.JWTInfo;

import java.util.Optional;

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
    private JwtTokenUtil jwtTokenUtil;

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
            String token = jwtTokenUtil.generateToken(new JWTInfo(user.getUserName(), user.getId() + "", user.getUserName()));
            return new JwtAuthenticationResponse(token);
        } catch (Exception e) {
            throw new JwtAuthException(this.messageResource.getMessage("auth.token.create.error"));
        }
    }
}
