package org.tlh.exam.auth;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/24
 * <p>
 * Github: https://github.com/tlhhup
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AuthClientAutoConfiguration.class)
public @interface EnableAuthClient {
}
