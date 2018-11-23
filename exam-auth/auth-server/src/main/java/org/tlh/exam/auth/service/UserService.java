package org.tlh.exam.auth.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.auth.entity.User;
import org.tlh.exam.auth.enums.UserType;
import org.tlh.exam.auth.model.UserDto;
import org.tlh.exam.auth.model.UserResDto;
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
    public boolean saveUser(UserDto userDto) {
        try {
            User user=new User();
            user.setUserName(userDto.getUserName());
            user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
            user.setUserType(userDto.getUserType().getCode());
            user.setIsActive(userDto.isActive());
            user.setCreateTime(userDto.getCreateTime());
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

    public List<UserResDto> findAll() {
        List<User> users = this.userRepository.findAll();
        List<UserResDto> collect = Optional.ofNullable(users).orElse(Lists.newArrayList()).stream()//
            .map(user -> dealUserInfo(user)).collect(Collectors.toList());
        return collect;
    }

    public UserResDto findUserDetail(int id){
        Optional<User> user = this.userRepository.findById(id);
        if(user.isPresent()){
            return null;
        }
        return dealUserInfo(user.get());
    }

    private UserResDto dealUserInfo(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setActive(user.getIsActive());
        userDto.setCreateTime(user.getCreateTime());
        userDto.setUserType(UserType.getUserTypeByCode(user.getUserType()));
        return userDto;
    }

}
