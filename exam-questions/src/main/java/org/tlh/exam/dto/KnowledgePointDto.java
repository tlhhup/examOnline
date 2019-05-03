package org.tlh.exam.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
public class KnowledgePointDto extends KnowledgePointBaseDto {

    private String description;
    private boolean isActive;
    private Date createTime;
    private Date updateTime;
    private int subjectId;

}
