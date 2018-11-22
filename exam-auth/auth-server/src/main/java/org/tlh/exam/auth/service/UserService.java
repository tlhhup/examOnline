package org.tlh.exam.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.auth.entity.User;
import org.tlh.exam.auth.model.UserResDto;
import org.tlh.exam.auth.repository.UserRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Pbkdf2PasswordEncoder passwordEncoder;

    @Transactional
    public boolean saveUser(UserResDto userResDto) {
        try {
            User user=new User();
            user.setUserName(userResDto.getUserName());
            user.setPassword(this.passwordEncoder.encode(userResDto.getPassword()));
            user.setUserType(userResDto.getUserType().getCode());
            user.setIsActive(userResDto.isActive());
            user.setCreateTime(userResDto.getCreateTime());
            this.userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.error("add user error",e.getMessage());
        }
        return false;
    }
}
