package org.tlh.exam.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.tlh.exam.auth.util.jwt.RsaKeyHelper;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/28
 * <p>
 * Github: https://github.com/tlhhup
 */
@Configuration
public class KeyConfiguration {

    @Autowired
    private ExamAuthServerProperties examAuthServerProperties;

    private byte[] userPubKey;
    private byte[] userPriKey;
    private byte[] servicePriKey;
    private byte[] servicePubKey;

    @PostConstruct
    public void init() throws Exception{
        //client端
        Map<String, byte[]> keyMap = RsaKeyHelper.generateKey(examAuthServerProperties.getClientRsaSecret());
        this.userPriKey=keyMap.get("pri");
        this.userPubKey=keyMap.get("pub");
        //server端
        keyMap=RsaKeyHelper.generateKey(examAuthServerProperties.getServerRsaSecret());
        this.servicePriKey=keyMap.get("pri");
        this.servicePubKey=keyMap.get("pub");
    }

    public byte[] getUserPubKey() {
        return userPubKey;
    }

    public byte[] getUserPriKey() {
        return userPriKey;
    }

    public byte[] getServicePriKey() {
        return servicePriKey;
    }

    public byte[] getServicePubKey() {
        return servicePubKey;
    }
}
