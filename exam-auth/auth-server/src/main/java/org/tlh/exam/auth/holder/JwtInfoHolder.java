package org.tlh.exam.auth.holder;

import org.tlh.exam.auth.util.jwt.IJWTInfo;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/31
 * <p>
 * Github: https://github.com/tlhhup
 */
public class JwtInfoHolder {

    private static final ThreadLocal<IJWTInfo> localeHolder =new ThreadLocal<>();
    //处理线程切换子线程中无法获取对象的问题，InheritableThreadLocal会将父线程的数据进行拷贝到自己的线程中
    private static final ThreadLocal<IJWTInfo> inheritableLocaleHolder =new InheritableThreadLocal<>();

    public static void setIJWTInfo(IJWTInfo ijwtInfo){
        if (ijwtInfo==null){
            resetIJWTInfo();
        }else{
            localeHolder.set(ijwtInfo);
            inheritableLocaleHolder.set(ijwtInfo);
        }
    }

    public static IJWTInfo getIJWTInfo(){
        IJWTInfo locale = localeHolder.get();
        if (locale==null){
            locale=inheritableLocaleHolder.get();
        }
        return locale;
    }

    public static void resetIJWTInfo(){
        localeHolder.remove();
        inheritableLocaleHolder.remove();
    }

}
