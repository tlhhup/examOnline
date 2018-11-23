package org.tlh.exam.auth.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.auth.entity.User;
import org.tlh.exam.auth.enums.UserType;
import org.tlh.exam.auth.model.UserAddDto;
import org.tlh.exam.auth.model.UserRespDto;
import org.tlh.exam.auth.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Pbkdf2PasswordEncoder passwordEncoder;

    @Transactional
    public boolean saveUser(UserAddDto userAddDto) {
        try {
            User user=new User();
            user.setUserName(userAddDto.getUserName());
            user.setPassword(this.passwordEncoder.encode(userAddDto.getPassword()));
            user.setUserType(userAddDto.getUserType().getCode());
            user.setIsActive(userAddDto.isActive());
            user.setCreateTime(userAddDto.getCreateTime());
            this.userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.error("add user error",e.getMessage());
        }
        return false;
    }

    @Transactional
    public boolean deleteUser(int id){
        try {
            this.userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("delete user error",e.getMessage());
        }
        return false;
    }

    @Transactional
    public boolean modifyPassword(int id,String password){
        return this.userRepository.updatePasswordById(id,this.passwordEncoder.encode(password))>0;
    }

    @Transactional
    public boolean modifyUserStatus(int id, boolean active) {
        return this.userRepository.updateUserActiveById(id,active)>0;
    }

    public List<UserRespDto> findAll() {
        List<User> users = this.userRepository.findAll();
        List<UserRespDto> collect = Optional.ofNullable(users).orElse(Lists.newArrayList()).stream()//
            .map(user -> dealUserInfo(user)).collect(Collectors.toList());
        return collect;
    }

    public UserRespDto findUserDetail(int id){
        Optional<User> user = this.userRepository.findById(id);
        if(!user.isPresent()){
            return null;
        }
        return dealUserInfo(user.get());
    }

    private UserRespDto dealUserInfo(User user) {
        UserRespDto userRespDto = new UserRespDto();
        userRespDto.setId(user.getId());
        userRespDto.setUserName(user.getUserName());
        userRespDto.setActive(user.getIsActive());
        userRespDto.setCreateTime(user.getCreateTime());
        userRespDto.setUserType(UserType.getUserTypeByCode(user.getUserType()));
        return userRespDto;
    }

}
