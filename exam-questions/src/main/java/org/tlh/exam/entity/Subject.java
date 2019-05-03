package org.tlh.exam.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
public class Subject {

    private Integer id;
    private String name;
    private String description;
    private Date createTime;
    private Date updateTime;
    private Boolean isActive;

    //关联的知识点数据
    private List<KnowledgePoint> knowledgePoints;

}
