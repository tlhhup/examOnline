package org.tlh.exam.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/3
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
public class SubjectWithPointDto extends SubjectBaseDto {

    private List<KnowledgePointBaseDto> points;

}
