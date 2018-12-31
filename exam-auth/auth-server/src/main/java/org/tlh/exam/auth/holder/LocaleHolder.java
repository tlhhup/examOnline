package org.tlh.exam.auth.holder;

import java.util.Locale;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/31
 * <p>
 * Github: https://github.com/tlhhup
 */
public class LocaleHolder {

    private static final ThreadLocal<Locale> localeHolder =new ThreadLocal<>();
    //处理线程切换子线程中无法获取对象的问题，InheritableThreadLocal会将父线程的数据进行拷贝到自己的线程中
    private static final ThreadLocal<Locale> inheritableLocaleHolder =new InheritableThreadLocal<>();

    public static void setLocale(Locale locale){
        if (locale==null){
            resetLocal();
        }else{
            localeHolder.set(locale);
            inheritableLocaleHolder.set(locale);
        }
    }

    public static Locale getLocal(){
        Locale locale = localeHolder.get();
        if (locale==null){
            locale=inheritableLocaleHolder.get();
        }
        return locale;
    }

    public static void resetLocal(){
        localeHolder.remove();
        inheritableLocaleHolder.remove();
    }

}
