package org.tlh.exam.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.auth.feign.AuthUserClient;
import org.tlh.exam.auth.model.req.UserAddDto;
import org.tlh.exam.auth.model.resp.ResponseDto;
import org.tlh.exam.auth.model.resp.UserRespDto;
import org.tlh.exam.user.repository.UserRepository;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/6
 * <p>
 * Github: https://github.com/tlhhup
 */
@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthUserClient authUserClient;

    public boolean creatUser(){
        UserAddDto userAddDto = new UserAddDto();
        ResponseDto<UserRespDto> userRespDtoResponseDto = this.authUserClient.addUser(userAddDto);
        if(userRespDtoResponseDto!=null&&userRespDtoResponseDto.getData()!=null){
            UserRespDto user = userRespDtoResponseDto.getData();
            if(user.getId()!=null){
                return true;
            }
        }
        return false;
    }

}
