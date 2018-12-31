package org.tlh.exam.auth.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.tlh.exam.auth.holder.LocaleHolder;

import java.util.Locale;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/28
 * <p>
 * Github: https://github.com/tlhhup
 */
@Component
public class LocaleMessageResource {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code){
        return this.getMessage(code,null);
    }

    public String getMessage(String code,Object[] args){
        return this.getMessage(code, args, LocaleHolder.getLocal());
    }

    public String getMessage(String code, Object[] args, Locale locale){
        return this.messageSource.getMessage(code,args,locale);
    }

}
