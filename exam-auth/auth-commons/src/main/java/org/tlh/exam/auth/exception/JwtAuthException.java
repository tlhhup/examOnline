package org.tlh.exam.auth.exception;

import org.tlh.exam.auth.constatns.CommonConstants;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/28
 * <p>
 * Github: https://github.com/tlhhup
 */
public class JwtAuthException extends RuntimeException {

    private int code = CommonConstants.EX_TOKEN_ERROR_CODE;

    public JwtAuthException(String message) {
        super(message);
    }

    public JwtAuthException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
