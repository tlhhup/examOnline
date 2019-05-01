package org.tlh.exam.controller;

import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.tlh.exam.dto.QuestionTypeDto;
import org.tlh.exam.service.QuestionTypeService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@RunWith(SpringRunner.class)
@WebMvcTest(QuestionTypeController.class)
public class QuestionTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionTypeService questionTypeService;

    @Test
    public void findAllTypes() {

    }

    @Test
    public void detail() throws Exception {
        QuestionTypeDto questionTypeDto=new QuestionTypeDto();
        questionTypeDto.setName("选择题");
        given(this.questionTypeService.getDetailById(1)).willReturn(questionTypeDto);
        this.mockMvc.perform(get("/QuestionType/detail/1")).andExpect(status().isOk()).andExpect(content().string(StringContains.containsString("选择题")));
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void active() {
    }

    @Test
    public void delete() {
    }
}