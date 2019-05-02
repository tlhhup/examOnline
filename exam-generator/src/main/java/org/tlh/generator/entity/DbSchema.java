package org.tlh.generator.entity;

import lombok.Data;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/2
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
public class DbSchema {

    private String dbName;
    private String characterSetName;
    private String collationName;

}

