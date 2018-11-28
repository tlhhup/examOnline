package org.tlh.exam.auth.util;

import java.util.Optional;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/26
 * <p>
 * Github: https://github.com/tlhhup
 */
public final class TokenHolder {

    private TokenHolder() {
    }

    private static ThreadLocal<String> requestVariables = new InheritableThreadLocal<>();

    /**
     * 初始化token
     * @param token
     */
    public static void initializeToken(String token) {
        requestVariables.set(token);
    }

    /**
     * 获取token
     * @return
     */
    public static String getTokenForCurrentThread() {
        String token = requestVariables.get();
        return Optional.ofNullable(token).orElse(null);
    }

    /**
     * 清除token
     */
    public static void cleanTokenForCurrentThread(){
        requestVariables.remove();
    }

}
