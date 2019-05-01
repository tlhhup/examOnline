package org.tlh.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.dto.SubjectDto;
import org.tlh.exam.entity.KnowledgePoint;
import org.tlh.exam.entity.Subject;
import org.tlh.exam.mapper.SubjectMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@Service
@Transactional(readOnly = true)
public class SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    public org.tlh.exam.model.PageInfo findAll(int page,int size){
        PageHelper.startPage(page,size);
        List<Subject> types = this.subjectMapper.findAll();
        PageInfo<Subject> pageInfo = new PageInfo<>(types);
        if (pageInfo != null && !pageInfo.getList().isEmpty()) {
            List<SubjectDto> results = pageInfo.getList().parallelStream().map(this::dealSubject2Dto).collect(Collectors.toList());
            org.tlh.exam.model.PageInfo result=new org.tlh.exam.model.PageInfo();
            result.setItems(results);
            result.setTotal(pageInfo.getTotal());
            return result;
        }
        return null;
    }


    public SubjectDto getDetailById(int id) {
        Subject subject = this.subjectMapper.findSubjectById(id);
        return subject != null ? dealSubject2Dto(subject) : null;
    }

    @Transactional
    public boolean saveSubject(SubjectDto subjectDto){
        Subject subject = this.dealDto2subject(subjectDto);
        return this.subjectMapper.insertSubject(subject)>0;
    }


    @Transactional
    public boolean updateSubject(SubjectDto subjectDtoubject){
        Subject subject = this.dealDto2subject(subjectDtoubject);
        return this.subjectMapper.updateSubject(subject)>0;
    }

    @Transactional
    public boolean updateSubjectStatus(int id,boolean active){
        return this.subjectMapper.updateSubjectStatus(id, active)>0;
    }

    @Transactional
    public boolean deleteSubjectById(int id){
        return this.subjectMapper.deleteSubject(id)>0;
    }

    private SubjectDto dealSubject2Dto(Subject subject) {
        SubjectDto result=new SubjectDto();
        BeanUtils.copyProperties(subject,result);
        result.setActive(subject.getIsActive());
        return result;
    }

    private Subject dealDto2subject(SubjectDto subjectDto) {
        Subject result=new Subject();
        BeanUtils.copyProperties(subjectDto,result);
        result.setIsActive(subjectDto.isActive());
        return result;
    }

}
