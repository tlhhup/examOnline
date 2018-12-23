package org.tlh.exam.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tlh.exam.auth.fallback.AuthUserClientFallback;
import org.tlh.exam.auth.model.req.UserAddDto;
import org.tlh.exam.auth.model.resp.UserRespDto;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/23
 * <p>
 * Github: https://github.com/tlhhup
 */
@FeignClient(name = "exam-auth-server", path = "/authUser",fallback = AuthUserClientFallback.class)
public interface AuthUserClient {

    /**
     * 添加用户
     * @param userAddDto
     * @return
     */
    @PostMapping("/add")
    ResponseEntity<Boolean> addUser(@RequestBody UserAddDto userAddDto);

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    ResponseEntity<Boolean> deleteUser(@PathVariable("id") int id);

    /**
     * 修改密码
     * @param id
     * @param password
     * @return
     */
    @PutMapping("/modify/{id}/pwd")
    ResponseEntity<Boolean> modifyPassword(@PathVariable("id") int id, @RequestParam("password") String password);

    /**
     * 激活(禁用)用户
     * @param id
     * @param active
     * @return
     */
    @PutMapping("/modify/{id}/active")
    ResponseEntity<Boolean> activeUser(@PathVariable("id") int id, @RequestParam("active") boolean active);

    /**
     * 用户详情
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    UserRespDto getUserDetail(@PathVariable("id") int id);

}
