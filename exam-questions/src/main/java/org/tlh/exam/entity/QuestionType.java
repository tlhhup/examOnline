package org.tlh.exam.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by 离歌笑tlh/hu ping on 2019/4/28
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
public class QuestionType {

    private Integer id;
    private String name;
    private String description;
    private Date createTime;
    private Date updateTime;
    private Boolean isActive;

}
