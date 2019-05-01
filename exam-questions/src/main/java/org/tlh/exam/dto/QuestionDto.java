package org.tlh.exam.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
public class QuestionDto implements Serializable {

    private int id;
    private String name;
    private String content;
    private int duration;
    private int points;
    private String answer;
    private int difficulty;
    private String analysis;
    private String reference;
    private String examingPoint;
    private String keyword;
    private int questionTypeId;
    private String creatorName;
    private Date createTime;
    private Date updateTime;
    private boolean isActive;

}
