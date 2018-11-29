package org.tlh.exam.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/29
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {

    public static final int ok = 200;
    public static final int BAD_REQUEST = 400;
    public static final int request_forbidden = 403;

    private int code;
    private T data;
    private String message;

    public static ResponseDto ok(String message) {
        return new ResponseDto(ok, null, message);
    }

    public static ResponseDto ok(int code, String message) {
        return new ResponseDto(code, null, message);
    }

}
