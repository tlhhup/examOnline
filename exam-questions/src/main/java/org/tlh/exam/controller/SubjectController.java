package org.tlh.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tlh.exam.dto.SubjectDto;
import org.tlh.exam.model.ResponseDto;
import org.tlh.exam.service.SubjectService;

import java.util.List;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@RestController
@RequestMapping("/Subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/list")
    public ResponseDto list(){
        List<SubjectDto> all = this.subjectService.findAll();
        return ResponseDto.ok(all);
    }

    @GetMapping("/detail/{id}")
    public ResponseDto<SubjectDto> detail(@PathVariable("id") int id){
        SubjectDto detail = this.subjectService.getDetailById(id);
        return ResponseDto.ok(detail);
    }

    @PostMapping("/create")
    public ResponseDto<Boolean> save(@RequestBody SubjectDto subjectDto){
        boolean flag = this.subjectService.saveSubject(subjectDto);
        return ResponseDto.ok(flag);
    }

    @PutMapping("/update")
    public ResponseDto<Boolean> update(@RequestBody SubjectDto subjectDto){
        boolean flag = this.subjectService.updateSubject(subjectDto);
        return ResponseDto.ok(flag);
    }

    @PutMapping("/active/{id}/{active}")
    public ResponseDto<Boolean> active(@PathVariable("id") int id,@PathVariable("active") boolean active){
        boolean flag = this.subjectService.updateSubjectStatus(id, active);
        return ResponseDto.ok(flag);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<Boolean> delete(@PathVariable("id") int id){
        boolean flag = this.subjectService.deleteSubjectById(id);
        return ResponseDto.ok(flag);
    }

}
