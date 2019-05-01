package org.tlh.exam.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.auth.constatns.CommonConstants;
import org.tlh.exam.auth.entity.Role;
import org.tlh.exam.auth.entity.User;
import org.tlh.exam.auth.enums.UserType;
import org.tlh.exam.auth.exception.JwtAuthException;
import org.tlh.exam.auth.exception.UserNotFoundException;
import org.tlh.exam.auth.model.req.AssignRoleReqDto;
import org.tlh.exam.auth.model.req.UserAddDto;
import org.tlh.exam.auth.model.resp.UserInfoRespDto;
import org.tlh.exam.auth.model.resp.UserRespDto;
import org.tlh.exam.auth.repository.UserRepository;
import org.tlh.exam.auth.util.LocaleMessageResource;

import java.util.*;
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

    @Value("${exam.auth.plain-password}")
    private String plainPassword;

    @Transactional
    public UserRespDto saveUser(UserAddDto userAddDto) {
        UserRespDto result=new UserRespDto();
        BeanUtils.copyProperties(userAddDto,result);
        try {
            User user = new User();
            user.setUserName(userAddDto.getUserName());
            user.setPassword(this.passwordEncoder.encode(userAddDto.getPassword()));
            user.setUserType(userAddDto.getUserType().getCode());
            user.setIsActive(userAddDto.isActive());
            user.setCreateTime(userAddDto.getCreateTime());
            this.userRepository.save(user);
            //设置ID
            result.setId(user.getId());
        } catch (Exception e) {
            log.error("add user error", e.getMessage());
        }
        return result;
    }

    @Transactional
    @CacheEvict(value = CommonConstants.AUTH, key = "'user:'+#id")
    public boolean deleteUser(int id) {
        try {
            this.userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("delete user error", e.getMessage());
        }
        return false;
    }

    public boolean checkPassword(int id, String password) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            return this.passwordEncoder.matches(password, user.get().getPassword());
        }
        return false;
    }

    @Transactional
    @CacheEvict(value = CommonConstants.AUTH, key = "'user:'+#id")
    public boolean modifyPassword(int id, String password) {
        return this.userRepository.updatePasswordById(id, this.passwordEncoder.encode(password)) > 0;
    }

    @Transactional
    public boolean resetPassword(int id) {
        String password = this.passwordEncoder.encode(plainPassword);
        return this.userRepository.updatePasswordById(id, password) > 0;
    }

    @Transactional
    @CacheEvict(value = CommonConstants.AUTH, key = "'user:'+#id")
    public boolean modifyUserStatus(int id, boolean active) {
        return this.userRepository.updateUserActiveById(id, active) > 0;
    }

    public Page<UserRespDto> findAll(String userName, Integer userType, Pageable pageable) {
        User query = new User();
        query.setUserType(userType);
        query.setUserName(userName);
        ExampleMatcher matcher = ExampleMatcher.matching()//
                .withMatcher("userName", match -> match.startsWith())
                .withMatcher("userType", matcher1 -> matcher1.storeDefaultMatching());
        Example<User> example = Example.of(query, matcher);
        Page<User> userPage = this.userRepository.findAll(example, pageable);
        List<UserRespDto> collect = userPage.stream().map(user -> dealUserInfo(user)).collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, userPage.getTotalElements());
    }

    @Cacheable(value = CommonConstants.AUTH, key = "'user:'+#id")
    public UserRespDto findUserDetail(int id) {
        Optional<User> user = this.userRepository.findById(id);
        if (!user.isPresent()) {
            return null;
        }
        return dealUserInfo(user.get());
    }

    @Cacheable(value = CommonConstants.AUTH, key = "'info:'+#id")
    public UserInfoRespDto findUserInfoById(int id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            User userInfo = user.get();
            UserInfoRespDto result = new UserInfoRespDto();
            result.setId(userInfo.getId());
            result.setName(userInfo.getUserName());
            // todo 以下数据处理
            result.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            result.setIntroduction("管理员");
            Set<String> roles = new HashSet<>();
            Set<String> permissions = new HashSet<>();
            result.setRoles(roles);
            result.setPermissions(permissions);
            userInfo.getRoles().forEach(role -> {
                roles.add(role.getRoleName());
                role.getPermissions().forEach(permission ->permissions.add(permission.getPermission()));
            });
            return result;
        } else {
            throw new JwtAuthException(this.messageResource.getMessage("auth.user.info.error"));
        }
    }

    @Transactional
    @CacheEvict(value = CommonConstants.AUTH, key = "'user:'+#assignRole.id")
    public boolean assignRole(AssignRoleReqDto assignRole) {
        //先获取用户信息
        Optional<User> user = this.userRepository.findById(assignRole.getId());
        if (!user.isPresent()) {
            throw new UserNotFoundException(this.messageResource.getMessage("auth.user.not.found"));
        }
        //利用持久态数据特点
        if (assignRole.getRoleIds() != null && assignRole.getRoleIds().length > 0) {
            List<Role> roles = Arrays.stream(assignRole.getRoleIds()).mapToObj(id -> {
                Role role = new Role();
                role.setId(id);
                return role;
            }).collect(Collectors.toList());
            user.get().setRoles(roles);
        } else {
            user.get().getRoles().clear();
        }
        return true;
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
