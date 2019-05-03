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
import org.tlh.exam.dto.QuestionTagDto;
import org.tlh.exam.model.PageInfo;
import org.tlh.exam.model.ResponseDto;
import org.tlh.exam.service.QuestionTagService;

import java.util.List;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@RestController
@RequestMapping("/QuestionTag")
public class QuestionTagController {

    @Autowired
    private QuestionTagService questionTagService;

    @GetMapping("/list")
    public ResponseDto findAll(@RequestParam(name = "page",defaultValue = "1") int page,
                               @RequestParam(name = "size",defaultValue = "20") int size,
                               @RequestParam(name = "tagName",required = false)String name){
        PageInfo all = this.questionTagService.findAll(page, size,name);
        return ResponseDto.ok(all);
    }

    @GetMapping("/all")
    public ResponseDto findAll(){
        List<QuestionTagDto> all = this.questionTagService.findAll();
        return ResponseDto.ok(all);
    }

    @GetMapping("/detail/{id}")
    public ResponseDto<QuestionTagDto> detail(@PathVariable(name = "id") int id){
        QuestionTagDto detail = this.questionTagService.detailById(id);
        return ResponseDto.ok(detail);
    }

    @PostMapping("/save")
    public ResponseDto<Boolean> save(@RequestBody QuestionTagDto knowledgePointDto){
        boolean flag = this.questionTagService.create(knowledgePointDto);
        return ResponseDto.ok(flag);
    }

    @PutMapping("/update")
    public ResponseDto<Boolean> update(@RequestBody QuestionTagDto knowledgePointDto){
        boolean flag = this.questionTagService.update(knowledgePointDto);
        return ResponseDto.ok(flag);
    }

    @PutMapping("/active/{id}/{active}")
    public ResponseDto<Boolean> active(@PathVariable("id") int id,@PathVariable("active") boolean active){
        boolean flag = this.questionTagService.active(id, active);
        return ResponseDto.ok(flag);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<Boolean> delete(@PathVariable("id") int id){
        boolean flag = this.questionTagService.deleteById(id);
        return ResponseDto.ok(flag);
    }

}
