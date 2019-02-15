package org.tlh.exam.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.user.repository.StudentRepository;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/6
 * <p>
 * Github: https://github.com/tlhhup
 */
@Service
@Transactional(readOnly = true)
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

}
