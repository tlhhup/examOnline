package org.tlh.exam.exceptions;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/31
 * <p>
 * Github: https://github.com/tlhhup
 */
public class IllegalRequestException extends RuntimeException{

    private int code;

    public IllegalRequestException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
