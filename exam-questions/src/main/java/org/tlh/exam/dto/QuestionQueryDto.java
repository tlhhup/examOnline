package org.tlh.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/3
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionQueryDto {
    private String name;
    private Integer questionTypeId;
    private Integer subjectId;
    private Integer knowledgePointId;
    private Integer questionTagId;
}
