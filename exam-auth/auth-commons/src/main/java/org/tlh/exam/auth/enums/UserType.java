package org.tlh.exam.auth.enums;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/22
 * <p>
 * Github: https://github.com/tlhhup
 */
public enum UserType {

    /**
     * 管理员
     */
    ADMIN(1, "admin"),

    /**
     * 教师
     */
    TEACHER(2, "teacher"),

    /**
     * 学生
     */
    STUDENT(3, "student");

    private int code;
    private String value;

    UserType(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
