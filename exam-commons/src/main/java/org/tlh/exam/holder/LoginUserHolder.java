package org.tlh.exam.holder;

import org.tlh.exam.auth.model.resp.UserInfoRespDto;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/31
 * <p>
 * Github: https://github.com/tlhhup
 */
public class LoginUserHolder {

    private static final ThreadLocal<UserInfoRespDto> localeHolder =new ThreadLocal<>();
    //处理线程切换子线程中无法获取对象的问题，InheritableThreadLocal会将父线程的数据进行拷贝到自己的线程中
    private static final ThreadLocal<UserInfoRespDto> inheritableLocaleHolder =new InheritableThreadLocal<>();

    public static void setLoginUser(UserInfoRespDto ijwtInfo){
        if (ijwtInfo==null){
            resetLoginUser();
        }else{
            localeHolder.set(ijwtInfo);
            inheritableLocaleHolder.set(ijwtInfo);
        }
    }

    public static UserInfoRespDto getLoginUser(){
        UserInfoRespDto locale = localeHolder.get();
        if (locale==null){
            locale=inheritableLocaleHolder.get();
        }
        return locale;
    }

    public static void resetLoginUser(){
        localeHolder.remove();
        inheritableLocaleHolder.remove();
    }

}
