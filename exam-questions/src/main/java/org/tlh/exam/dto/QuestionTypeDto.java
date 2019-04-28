package org.tlh.exam.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 离歌笑tlh/hu ping on 2019/4/28
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
public class QuestionTypeDto implements Serializable {

    private int id;
    private String name;
    private String description;
    private Date createTime;
    private Date updateTime;
    private boolean isActive;

}
