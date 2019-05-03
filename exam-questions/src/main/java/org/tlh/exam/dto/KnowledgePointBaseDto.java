package org.tlh.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/3
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgePointBaseDto implements Serializable {

    private int id;
    private String pointName;
}
