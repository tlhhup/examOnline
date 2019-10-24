package org.tlh.exam.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.tlh.exam.entity.KnowledgePoint;

import java.util.List;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@Mapper
public interface KnowledgePointMapper {

    List<KnowledgePoint> findAll(@Param("name") String name);

    List<KnowledgePoint> findActiveAll();

    KnowledgePoint detailById(int id);

    int save(KnowledgePoint knowledgePoint);

    int update(KnowledgePoint knowledgePoint);

    int active(@Param("id")int id,@Param("active") boolean active);

    int deleteById(int id);

    /**
     * 获取考点名称
     * @param examPoints
     * @return
     */
    String findKnowledgeNamesByIds(@Param("ids") int[] examPoints);

    /**
     * 保存考点和问题关联数据
     * @param qId
     * @param pointIds
     */
    void insertQuestionLinks(@Param("qId") Integer qId, @Param("pointIds") int[] pointIds);

    /**
     * 删除问题关联
     * @param id
     * @param isQuestionId
     */
    void deleteQuestionLinks(@Param("id") int id, @Param("isQuestionId") boolean isQuestionId);
}
