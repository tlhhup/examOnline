package org.tlh.exam.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.auth.constatns.CommonConstants;
import org.tlh.exam.auth.entity.User;
import org.tlh.exam.auth.enums.UserType;
import org.tlh.exam.auth.exception.JwtAuthException;
import org.tlh.exam.auth.model.req.UserAddDto;
import org.tlh.exam.auth.model.resp.UserInfoRespDto;
import org.tlh.exam.auth.model.resp.UserRespDto;
import org.tlh.exam.auth.repository.UserRepository;
import org.tlh.exam.auth.util.LocaleMessageResource;

import java.util.Arrays;
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

    @Autowired
    private LocaleMessageResource messageResource;

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
    @CacheEvict(value = CommonConstants.AUTH,key = "'user:'+#id")
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
    @CacheEvict(value = CommonConstants.AUTH,key = "'user:'+#id")
    public boolean modifyPassword(int id,String password){
        return this.userRepository.updatePasswordById(id,this.passwordEncoder.encode(password))>0;
    }

    @Transactional
    @CacheEvict(value = CommonConstants.AUTH,key = "'user:'+#id")
    public boolean modifyUserStatus(int id, boolean active) {
        return this.userRepository.updateUserActiveById(id,active)>0;
    }

    public Page<UserRespDto> findAll(String userName, Integer userType, Pageable pageable) {
        User query=new User();
        query.setUserType(userType);
        query.setUserName(userName);
        ExampleMatcher matcher = ExampleMatcher.matching()//
                .withMatcher("userName", match->match.startsWith())
                .withMatcher("userType", matcher1 -> matcher1.storeDefaultMatching());
        Example<User> example = Example.of(query, matcher);
        Page<User> userPage = this.userRepository.findAll(example,pageable);
        List<UserRespDto> collect = userPage.stream().map(user -> dealUserInfo(user)).collect(Collectors.toList());
        return new PageImpl<>(collect,pageable,userPage.getTotalElements());
    }

    @Cacheable(value = CommonConstants.AUTH,key = "'user:'+#id")
    public UserRespDto findUserDetail(int id){
        Optional<User> user = this.userRepository.findById(id);
        if(!user.isPresent()){
            return null;
        }
        return dealUserInfo(user.get());
    }

    @Cacheable(value = CommonConstants.AUTH,key = "'info:'+#id")
    public UserInfoRespDto findUserInfoById(int id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()){
            User userInfo = user.get();
            UserInfoRespDto result=new UserInfoRespDto();
            result.setName(userInfo.getUserName());
            // todo 以下数据处理
            result.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            result.setIntroduction("管理员");
            result.setRoles(Arrays.asList("admin","editor"));
            return result;
        }else{
            throw new JwtAuthException(this.messageResource.getMessage("auth.user.info.error"));
        }
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
