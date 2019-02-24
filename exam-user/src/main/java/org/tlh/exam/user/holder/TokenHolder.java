package org.tlh.exam.user.holder;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/24
 * <p>
 * Github: https://github.com/tlhhup
 */
public final class TokenHolder {

    private static final ThreadLocal<String> localeHolder =new ThreadLocal<>();
    //处理线程切换子线程中无法获取对象的问题，InheritableThreadLocal会将父线程的数据进行拷贝到自己的线程中
    private static final ThreadLocal<String> inheritableLocaleHolder =new InheritableThreadLocal<>();

    public static void setToken(String token){
        if (token==null){
            resetToken();
        }else{
            localeHolder.set(token);
            inheritableLocaleHolder.set(token);
        }
    }

    public static String getToken(){
        String locale = localeHolder.get();
        if (locale==null){
            locale=inheritableLocaleHolder.get();
        }
        return locale;
    }

    public static void resetToken(){
        localeHolder.remove();
        inheritableLocaleHolder.remove();
    }

}
