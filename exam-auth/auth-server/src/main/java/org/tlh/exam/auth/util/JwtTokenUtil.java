package org.tlh.exam.auth.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tlh.exam.auth.config.ExamAuthServerProperties;
import org.tlh.exam.auth.config.KeyConfiguration;
import org.tlh.exam.auth.util.jwt.IJWTInfo;
import org.tlh.exam.auth.util.jwt.JWTHelper;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/28
 * <p>
 * Github: https://github.com/tlhhup
 */
@Component
public class JwtTokenUtil {

    @Autowired
    private ExamAuthServerProperties examAuthServerProperties;

    @Autowired
    private KeyConfiguration keyConfiguration;

    /**
     * 生成token
     * @param jwtInfo
     * @return
     * @throws Exception
     */
    public String generateToken(IJWTInfo jwtInfo) throws Exception {
        return JWTHelper.generateToken(jwtInfo, keyConfiguration.getUserPriKey(),examAuthServerProperties.getJwtExpire());
    }

    /**
     * 解析用户信息
     * @param token
     * @return
     * @throws Exception
     */
    public IJWTInfo getInfoFromToken(String token) throws Exception {
        return JWTHelper.getInfoFromToken(token, keyConfiguration.getUserPubKey());
    }
}
