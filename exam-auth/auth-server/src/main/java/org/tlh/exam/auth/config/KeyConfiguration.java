package org.tlh.exam.auth.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/28
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@Configuration
public class KeyConfiguration {

    private byte[] userPubKey;
    private byte[] userPriKey;
    private byte[] servicePriKey;
    private byte[] servicePubKey;
}
