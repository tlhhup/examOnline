package org.tlh.exam.auth.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.tlh.exam.auth.config.ExamAuthServerProperties;
import org.tlh.exam.auth.config.KeyConfiguration;
import org.tlh.exam.auth.util.jwt.RsaKeyHelper;

import java.util.Map;

/**
 * Created by 离歌笑tlh/hu ping on 2019/1/12
 * <p>
 * Github: https://github.com/tlhhup
 */
@Slf4j
@Configuration
public class AuthServerRsaRunner implements CommandLineRunner {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ExamAuthServerProperties examAuthServerProperties;

    private static final String REDIS_USER_PRI_KEY = "EXAM:AUTH:CLIENT:PRI";
    private static final String REDIS_USER_PUB_KEY = "EXAM:AUTH:CLIENT:PUB";
    private static final String REDIS_SERVICE_PRI_KEY = "EXAM:AUTH:SERVER:PRI";
    private static final String REDIS_SERVICE_PUB_KEY = "EXAM:AUTH:SERVER:PUB";

    @Autowired
    private KeyConfiguration keyConfiguration;

    @Override
    public void run(String... args) throws Exception {
        if (redisTemplate.hasKey(REDIS_USER_PRI_KEY) && redisTemplate.hasKey(REDIS_USER_PUB_KEY) &&
                redisTemplate.hasKey(REDIS_SERVICE_PRI_KEY) && redisTemplate.hasKey(REDIS_SERVICE_PUB_KEY)) {
            log.info("load auth server rsa");
            keyConfiguration.setUserPriKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_USER_PRI_KEY).toString()));
            keyConfiguration.setUserPubKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_USER_PUB_KEY).toString()));
            keyConfiguration.setServicePriKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_SERVICE_PRI_KEY).toString()));
            keyConfiguration.setServicePubKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_SERVICE_PUB_KEY).toString()));
        } else {
            log.info("generate server rsa");
            //设置用户侧密钥
            Map<String, byte[]> keyMap = RsaKeyHelper.generateKey(examAuthServerProperties.getClientRsaSecret());
            keyConfiguration.setUserPriKey(keyMap.get("pri"));
            keyConfiguration.setUserPubKey(keyMap.get("pub"));
            redisTemplate.opsForValue().set(REDIS_USER_PRI_KEY, RsaKeyHelper.toHexString(keyMap.get("pri")));
            redisTemplate.opsForValue().set(REDIS_USER_PUB_KEY, RsaKeyHelper.toHexString(keyMap.get("pub")));
            //设置服务侧密钥
            keyMap = RsaKeyHelper.generateKey(examAuthServerProperties.getServerRsaSecret());
            keyConfiguration.setServicePriKey(keyMap.get("pri"));
            keyConfiguration.setServicePubKey(keyMap.get("pub"));
            redisTemplate.opsForValue().set(REDIS_SERVICE_PRI_KEY, RsaKeyHelper.toHexString(keyMap.get("pri")));
            redisTemplate.opsForValue().set(REDIS_SERVICE_PUB_KEY, RsaKeyHelper.toHexString(keyMap.get("pub")));
        }
    }
}
