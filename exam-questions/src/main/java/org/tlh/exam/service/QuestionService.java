package org.tlh.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.auth.model.resp.UserInfoRespDto;
import org.tlh.exam.dto.QuestionDto;
import org.tlh.exam.entity.Question;
import org.tlh.exam.holder.LoginUserHolder;
import org.tlh.exam.mapper.QuestionMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@Service
@Transactional(readOnly = true)
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    public org.tlh.exam.model.PageInfo findAll(int page,int size){
        PageHelper.startPage(page,size);
        List<Question> knowledgePoints = this.questionMapper.findAll();
        PageInfo<Question> pageInfo = new PageInfo<>(knowledgePoints);
        List<QuestionDto> results = pageInfo.getList().parallelStream().map(this::dealQuestion2Dto).collect(Collectors.toList());
        org.tlh.exam.model.PageInfo result=new org.tlh.exam.model.PageInfo();
        result.setItems(results);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    public QuestionDto detailById(int id){
        Question knowledgePoint = this.questionMapper.detailById(id);
        return knowledgePoint!=null?this.dealQuestion2Dto(knowledgePoint):null;
    }

    @Transactional
    public boolean create(QuestionDto questionDto){
        Question question = this.dealDto2Question(questionDto);
        int flag = this.questionMapper.save(question);
        return flag>0;
    }

    @Transactional
    public boolean deleteById(int id){
        int i = this.questionMapper.deleteById(id);
        return i>0;
    }

    @Transactional
    public boolean update(QuestionDto questionDto){
        Question question = this.dealDto2Question(questionDto);
        int update = this.questionMapper.update(question);
        return update>0;
    }

    @Transactional
    public boolean active(int id,boolean active){
        int active1 = this.questionMapper.active(id, active);
        return active1>0;
    }


    private QuestionDto dealQuestion2Dto(Question question){
        QuestionDto result=new QuestionDto();
        BeanUtils.copyProperties(question,result);
        result.setActive(question.getIsActive());
        result.setCreatorName(question.getCreatorName());
        return result;
    }

    private Question dealDto2Question(QuestionDto questionDto){
        Question result=new Question();
        BeanUtils.copyProperties(questionDto,result);
        result.setIsActive(questionDto.isActive());
        UserInfoRespDto loginUser = LoginUserHolder.getLoginUser();
        if(loginUser!=null){
            result.setCreatorId(loginUser.getId());
            result.setCreatorName(loginUser.getName());
        }
        return result;
    }

}
