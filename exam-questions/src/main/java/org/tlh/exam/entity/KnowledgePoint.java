package org.tlh.exam.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
public class KnowledgePoint {

    private Integer id;
    private String pointName;
    private String description;
    private Boolean isActive;
    private Date createTime;
    private Date updateTime;
    private Integer subjectId;

}
