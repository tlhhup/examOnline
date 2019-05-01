package org.tlh.exam.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.dto.QuestionTypeDto;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class QuestionTypeServiceTest {

    @Autowired
    private QuestionTypeService questionTypeService;

    @Test
    public void findAll() {
        List<QuestionTypeDto> all = this.questionTypeService.findAll();
        System.out.println(all);
    }

    @Test
    public void getDetailById() {
    }

    @Test
    public void saveQuestionType() {
        QuestionTypeDto questionTypeDto=new QuestionTypeDto();
        questionTypeDto.setActive(true);
        questionTypeDto.setName("选择题");
        questionTypeDto.setDescription("只有一个选项是争取的");
        this.questionTypeService.saveQuestionType(questionTypeDto);
    }

    @Test
    public void updateQuestionType() {
    }

    @Test
    public void updateQuestionTypeStatus() {
    }

    @Test
    public void deleteQuestionTypeById() {
    }
}