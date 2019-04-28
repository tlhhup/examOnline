package org.tlh.exam.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.tlh.exam.entity.QuestionType;

import java.util.List;

/**
 * Created by 离歌笑tlh/hu ping on 2019/4/28
 * <p>
 * Github: https://github.com/tlhhup
 */
@Mapper
public interface QuestionTypeMapper {

    List<QuestionType> findAll();

}
