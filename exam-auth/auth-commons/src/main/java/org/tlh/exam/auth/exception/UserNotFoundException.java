package org.tlh.exam.auth.exception;

import org.tlh.exam.auth.constatns.CommonConstants;

/**
 * Created by 离歌笑tlh/hu ping on 2019/1/27
 * <p>
 * Github: https://github.com/tlhhup
 */
public class UserNotFoundException extends RuntimeException{

    private int code = CommonConstants.USER_NOT_FOUND;

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(int code, String message) {
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
