package org.tlh.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.dto.QuestionTypeDto;
import org.tlh.exam.entity.QuestionType;
import org.tlh.exam.mapper.QuestionTypeMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 离歌笑tlh/hu ping on 2019/4/28
 * <p>
 * Github: https://github.com/tlhhup
 */
@Service
@Transactional(readOnly = true)
public class QuestionTypeService {

    @Autowired
    private QuestionTypeMapper questionTypeMapper;

    public org.tlh.exam.model.PageInfo findAll(int page,int size) {
        PageHelper.startPage(page,size);
        List<QuestionType> types = this.questionTypeMapper.findAll();
        PageInfo<QuestionType> pageInfo = new PageInfo<>(types);
        if (pageInfo != null && !pageInfo.getList().isEmpty()) {
            List<QuestionTypeDto> results = pageInfo.getList().parallelStream().map(this::dealQuestionType2Dto).collect(Collectors.toList());
            org.tlh.exam.model.PageInfo result=new org.tlh.exam.model.PageInfo();
            result.setTotal(pageInfo.getTotal());
            result.setItems(results);
            return result;
        }
        return null;
    }

    public QuestionTypeDto getDetailById(int id) {
        QuestionType questionType = this.questionTypeMapper.findQuestionTypeById(id);
        return questionType != null ? dealQuestionType2Dto(questionType) : null;
    }

    @Transactional
    public boolean saveQuestionType(QuestionTypeDto questionTypeDto){
        QuestionType questionType = this.dealDto2QuestionType(questionTypeDto);
        return this.questionTypeMapper.insertQuestionType(questionType)>0;
    }

    @Transactional
    public boolean updateQuestionType(QuestionTypeDto questionTypeDto){
        QuestionType questionType = this.dealDto2QuestionType(questionTypeDto);
        return this.questionTypeMapper.updateQuestionType(questionType)>0;
    }

    @Transactional
    public boolean updateQuestionTypeStatus(int id,boolean active){
        return this.questionTypeMapper.updateQuestionTypeStatus(id, active)>0;
    }

    @Transactional
    public boolean deleteQuestionTypeById(int id){
        return this.questionTypeMapper.deleteQuestionType(id)>0;
    }


    private QuestionTypeDto dealQuestionType2Dto(QuestionType questionType) {
        QuestionTypeDto result = new QuestionTypeDto();
        BeanUtils.copyProperties(questionType, result);
        result.setActive(questionType.getIsActive());
        return result;
    }

    private QuestionType dealDto2QuestionType(QuestionTypeDto questionTypeDto) {
        QuestionType result = new QuestionType();
        BeanUtils.copyProperties(questionTypeDto, result);
        result.setIsActive(questionTypeDto.isActive());
        return result;
    }

}
