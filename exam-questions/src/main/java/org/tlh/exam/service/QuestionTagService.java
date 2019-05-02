package org.tlh.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.dto.KnowledgePointDto;
import org.tlh.exam.dto.QuestionTagDto;
import org.tlh.exam.entity.KnowledgePoint;
import org.tlh.exam.entity.QuestionTag;
import org.tlh.exam.mapper.QuestionTagMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@Service
@Transactional(readOnly = true)
public class QuestionTagService {

    @Autowired
    private QuestionTagMapper questionTagMapper;

    public org.tlh.exam.model.PageInfo findAll(int page,int size,String name){
        PageHelper.startPage(page,size);
        List<QuestionTag> knowledgePoints = this.questionTagMapper.findAll(name);
        PageInfo<QuestionTag> pageInfo = new PageInfo<>(knowledgePoints);
        List<QuestionTagDto> results = pageInfo.getList().parallelStream().map(this::dealQuestionTag2Dto).collect(Collectors.toList());
        org.tlh.exam.model.PageInfo result=new org.tlh.exam.model.PageInfo();
        result.setItems(results);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    public QuestionTagDto detailById(int id){
        QuestionTag questionTag = this.questionTagMapper.detailById(id);
        return questionTag!=null?this.dealQuestionTag2Dto(questionTag):null;
    }

    @Transactional
    public boolean create(QuestionTagDto questionTagDto){
        QuestionTag questionTag = this.dealDto2QuestionTag(questionTagDto);
        int flag = this.questionTagMapper.save(questionTag);
        return flag>0;
    }

    @Transactional
    public boolean deleteById(int id){
        int i = this.questionTagMapper.deleteById(id);
        return i>0;
    }

    @Transactional
    public boolean update(QuestionTagDto questionTagDto){
        QuestionTag questionTag = this.dealDto2QuestionTag(questionTagDto);
        int update = this.questionTagMapper.update(questionTag);
        return update>0;
    }

    @Transactional
    public boolean active(int id,boolean active){
        int active1 = this.questionTagMapper.active(id, active);
        return active1>0;
    }


    private QuestionTagDto dealQuestionTag2Dto(QuestionTag questionTag){
        QuestionTagDto result=new QuestionTagDto();
        BeanUtils.copyProperties(questionTag,result);
        result.setActive(questionTag.getIsActive());
        return result;
    }

    private QuestionTag dealDto2QuestionTag(QuestionTagDto questionTagDto){
        QuestionTag result=new QuestionTag();
        BeanUtils.copyProperties(questionTagDto,result);
        result.setIsActive(questionTagDto.isActive());
        return result;
    }

}
