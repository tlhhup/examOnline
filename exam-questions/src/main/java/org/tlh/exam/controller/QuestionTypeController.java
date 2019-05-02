package org.tlh.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tlh.exam.dto.QuestionTypeDto;
import org.tlh.exam.model.PageInfo;
import org.tlh.exam.model.ResponseDto;
import org.tlh.exam.service.QuestionTypeService;


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
    public ResponseDto findAllTypes(@RequestParam(name = "page",defaultValue = "1") int page,
                                    @RequestParam(name = "size",defaultValue = "20") int size,
                                    @RequestParam(name = "name",required = false)String name){
        PageInfo all = this.questionTypeService.findAll(page, size,name);
        return ResponseDto.ok(all);
    }

    @GetMapping("/detail/{id}")
    public ResponseDto<QuestionTypeDto> detail(@PathVariable(name = "id") int id){
        QuestionTypeDto questionType = this.questionTypeService.getDetailById(id);
        return ResponseDto.ok(questionType);
    }

    @PostMapping("/save")
    public ResponseDto<Boolean> save(@RequestBody QuestionTypeDto questionTypeDto){
        boolean flag = this.questionTypeService.saveQuestionType(questionTypeDto);
        return ResponseDto.ok(flag);
    }

    @PutMapping("/update")
    public ResponseDto<Boolean> update(@RequestBody QuestionTypeDto questionTypeDto){
        boolean flag = this.questionTypeService.updateQuestionType(questionTypeDto);
        return ResponseDto.ok(flag);
    }

    @PutMapping("/active/{id}/{active}")
    public ResponseDto<Boolean> active(@PathVariable("id") int id,@PathVariable("active") boolean active){
        boolean flag = this.questionTypeService.updateQuestionTypeStatus(id, active);
        return ResponseDto.ok(flag);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<Boolean> delete(@PathVariable("id") int id){
        boolean flag = this.questionTypeService.deleteQuestionTypeById(id);
        return ResponseDto.ok(flag);
    }

}
