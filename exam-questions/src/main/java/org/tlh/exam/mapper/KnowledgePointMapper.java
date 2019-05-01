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

    List<KnowledgePoint> findAll();

    KnowledgePoint detailById(int id);

    int save(KnowledgePoint knowledgePoint);

    int update(KnowledgePoint knowledgePoint);

    int active(@Param("id")int id,@Param("active") boolean active);

    int deleteById(int id);

}
