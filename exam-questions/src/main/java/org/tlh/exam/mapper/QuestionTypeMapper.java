package org.tlh.exam.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.tlh.exam.entity.Question;
import org.tlh.exam.entity.QuestionType;

import java.util.List;

/**
 * Created by 离歌笑tlh/hu ping on 2019/4/28
 * <p>
 * Github: https://github.com/tlhhup
 */
@Mapper
public interface QuestionTypeMapper {

    List<QuestionType> findAll(@Param("name") String name);

    List<QuestionType> findActiveAll();

    QuestionType findQuestionTypeById(int id);

    int insertQuestionType(QuestionType questionType);

    int updateQuestionType(QuestionType questionType);

    int updateQuestionTypeStatus(@Param("id") int id, @Param("active") boolean active);

    int deleteQuestionType(int id);

}
