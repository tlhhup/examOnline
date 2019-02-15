package org.tlh.exam.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tlh.exam.user.service.UserService;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/5
 * <p>
 * Github: https://github.com/tlhhup
 */
@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

}
