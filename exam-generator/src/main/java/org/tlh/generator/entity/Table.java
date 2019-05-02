package org.tlh.generator.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/2
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
public class Table {

    private String tableName;
    private String engine;
    private String tableComment;
    private Date createTime;

}
