package org.tlh.exam.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.dto.SubjectDto;
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

    public List<SubjectDto> findAll() {
        List<Subject> types = this.subjectMapper.findAll();
        if (types != null && !types.isEmpty()) {
            List<SubjectDto> results = types.parallelStream().map(this::dealSubject2Dto).collect(Collectors.toList());
            return results;
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
