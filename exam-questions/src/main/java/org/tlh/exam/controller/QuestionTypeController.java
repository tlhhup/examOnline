package org.tlh.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tlh.exam.dto.QuestionTypeDto;
import org.tlh.exam.service.QuestionTypeService;

import java.util.List;

/**
 * Created by 离歌笑tlh/hu ping on 2019/4/28
 * <p>
 * Github: https://github.com/tlhhup
 */
@RestController
@RequestMapping("/QuestionType")
public class QuestionTypeController {

    @Autowired
    private QuestionTypeService questionTypeService;

    @GetMapping("/list")
    public List<QuestionTypeDto> findAllTypes(){
        return this.questionTypeService.findAll();
    }

}
