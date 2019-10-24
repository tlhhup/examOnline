package org.tlh.exam.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.tlh.exam.entity.QuestionTag;

import java.util.List;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@Mapper
public interface QuestionTagMapper {

    List<QuestionTag> findAll(@Param("name") String name);

    List<QuestionTag> findActiveAll();

    QuestionTag detailById(int id);

    int save(QuestionTag questionTag);

    int update(QuestionTag questionTag);

    int active(@Param("id")int id, @Param("active") boolean active);

    int deleteById(int id);

    /**
     * 保存问题标签关联
     * @param qId
     * @param tagId
     */
    void insertQuestionLinks(@Param("qId") Integer qId, @Param("tagId") int tagId);

    /**
     * 删除问题标签关联
     * @param id
     * @param isQuestion
     */
    void deleteQuestionLinks(@Param("id") int id,@Param("isQuestion") boolean isQuestion);
}
