package org.tlh.exam.service;

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

    public List<QuestionTypeDto> findAll(){
        List<QuestionType> types = this.questionTypeMapper.findAll();
        if(types!=null&&!types.isEmpty()){
            List<QuestionTypeDto> results = types.parallelStream().map(questionType -> {
                QuestionTypeDto result = new QuestionTypeDto();
                BeanUtils.copyProperties(questionType, result);
                return result;
            }).collect(Collectors.toList());
            return results;
        }
        return null;
    }

}
