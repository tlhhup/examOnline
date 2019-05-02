package org.tlh.exam.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.tlh.exam.entity.Subject;

import java.util.List;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@Mapper
public interface SubjectMapper {

    List<Subject> findAll(@Param("name") String name);

    List<Subject> allActive();

    Subject findSubjectById(int id);

    int insertSubject(Subject subject);

    int updateSubject(Subject subject);

    int updateSubjectStatus(@Param("id") int id, @Param("active") boolean active);

    int deleteSubject(int id);

}
