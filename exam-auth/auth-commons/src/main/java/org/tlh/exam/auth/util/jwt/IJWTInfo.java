package org.tlh.exam.auth.util.jwt;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/27
 * <p>
 * Github: https://github.com/tlhhup
 */
public interface IJWTInfo {

    /**
     * 获取用户名
     * @return
     */
    String getUniqueName();

    /**
     * 获取用户ID
     * @return
     */
    String getId();

    /**
     * 获取名称
     * @return
     */
    String getName();

}
