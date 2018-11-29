package org.tlh.exam.auth.config;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tlh.exam.auth.exception.JwtAuthException;
import org.tlh.exam.auth.model.ResponseDto;

import java.util.List;
import java.util.Map;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/29
 * <p>
 * Github: https://github.com/tlhhup
 */
@Component
@RestControllerAdvice
public class AuthServerExceptionHandler {

    @ExceptionHandler(JwtAuthException.class)
    public ResponseDto handlerAuthException(JwtAuthException authException){
        return ResponseDto.ok(authException.getCode(),authException.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto handlerValidationException(MethodArgumentNotValidException e){
        Map<String,String> data= Maps.newHashMap();
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        for(ObjectError error:allErrors){
            if(error instanceof FieldError) {
                data.put(((FieldError) error).getField(), error.getDefaultMessage());
            }
        }
        return new ResponseDto(ResponseDto.BAD_REQUEST,data,null);
    }

}
