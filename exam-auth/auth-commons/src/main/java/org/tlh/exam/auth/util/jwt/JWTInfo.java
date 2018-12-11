package org.tlh.exam.auth.util.jwt;

import java.io.Serializable;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/27
 * <p>
 * Github: https://github.com/tlhhup
 */
public class JWTInfo implements Serializable,IJWTInfo {

    private String username;
    private Integer userId;
    private String name;

    public JWTInfo(String username, Integer userId, String name) {
        this.username = username;
        this.userId = userId;
        this.name = name;
    }

    @Override
    public String getUniqueName() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Integer getId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JWTInfo)) {
            return false;
        }

        JWTInfo jwtInfo = (JWTInfo) o;

        if (username != null ? !username.equals(jwtInfo.username) : jwtInfo.username != null) {
            return false;
        }
        return userId != null ? userId.equals(jwtInfo.userId) : jwtInfo.userId == null;

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
