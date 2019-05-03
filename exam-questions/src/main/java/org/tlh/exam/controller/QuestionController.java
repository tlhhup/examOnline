package org.tlh.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tlh.exam.dto.QuestionDto;
import org.tlh.exam.dto.QuestionQueryDto;
import org.tlh.exam.model.PageInfo;
import org.tlh.exam.model.ResponseDto;
import org.tlh.exam.service.QuestionService;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@RestController
@RequestMapping("/Question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/list")
    public ResponseDto findAll(@RequestParam(name = "page",defaultValue = "1") int page,
                               @RequestParam(name = "size",defaultValue = "20") int size,
                               @RequestParam(name = "name",required = false)String name,
                               @RequestParam(name = "questionType",required = false)Integer questionTypeId,
                               @RequestParam(name = "subject",required = false)Integer subjectId,
                               @RequestParam(name = "knowledgePoint",required = false)Integer knowledgePointId,
                               @RequestParam(name = "questionTag",required = false)Integer questionTagId){
        QuestionQueryDto questionQueryDto = new QuestionQueryDto(name, questionTypeId, subjectId, knowledgePointId, questionTagId);
        PageInfo all = this.questionService.findAll(page, size, questionQueryDto);
        return ResponseDto.ok(all);
    }

    @GetMapping("/detail/{id}")
    public ResponseDto<QuestionDto> detail(@PathVariable(name = "id") int id){
        QuestionDto detail = this.questionService.detailById(id);
        return ResponseDto.ok(detail);
    }

    @PostMapping("/save")
    public ResponseDto<Boolean> save(@RequestBody QuestionDto knowledgePointDto){
        boolean flag = this.questionService.create(knowledgePointDto);
        return ResponseDto.ok(flag);
    }

    @PutMapping("/update")
    public ResponseDto<Boolean> update(@RequestBody QuestionDto knowledgePointDto){
        boolean flag = this.questionService.update(knowledgePointDto);
        return ResponseDto.ok(flag);
    }

    @PutMapping("/active/{id}/{active}")
    public ResponseDto<Boolean> active(@PathVariable("id") int id,@PathVariable("active") boolean active){
        boolean flag = this.questionService.active(id, active);
        return ResponseDto.ok(flag);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<Boolean> delete(@PathVariable("id") int id){
        boolean flag = this.questionService.deleteById(id);
        return ResponseDto.ok(flag);
    }

}
