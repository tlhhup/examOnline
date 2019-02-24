package org.tlh.exam.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tlh.exam.auth.model.resp.PageInfo;
import org.tlh.exam.auth.model.resp.ResponseDto;
import org.tlh.exam.user.model.resp.AdminRespDto;
import org.tlh.exam.user.service.AdminService;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/5
 * <p>
 * Github: https://github.com/tlhhup
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/list")
    public ResponseDto list(Pageable pageable,
                            @RequestParam(name = "userName", required = false) String userName) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
        Page<AdminRespDto> page = this.adminService.list(userName, pageRequest);
        PageInfo<AdminRespDto> pageInfo = new PageInfo<>(page.getContent(), page.getTotalElements());
        return ResponseDto.ok(pageInfo);
    }


}
