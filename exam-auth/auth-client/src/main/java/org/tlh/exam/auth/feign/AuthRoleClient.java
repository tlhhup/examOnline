package org.tlh.exam.auth.feign;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.tlh.exam.auth.fallback.AuthRoleClientFallback;
import org.tlh.exam.auth.model.req.RoleReqDto;
import org.tlh.exam.auth.model.resp.ResponseDto;
import org.tlh.exam.auth.model.resp.RoleRespDto;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/23
 * <p>
 * Github: https://github.com/tlhhup
 */
@FeignClient(name = "exam-auth-server",path = "/authRole",fallback = AuthRoleClientFallback.class)
public interface AuthRoleClient {

    /**
     * 添加角色
     * @param roleReqDto
     * @return
     */
    @PostMapping("/create")
    ResponseDto<Boolean> addRole(@RequestBody RoleReqDto roleReqDto);

    /**
     * 删除角色
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    ResponseDto<Boolean> deleteRole(@PathVariable("id")int id);

    /**
     * 修改角色
     * @param id
     * @param roleReqDto
     * @return
     */
    @PutMapping("/modify/{id}")
    ResponseDto<Boolean> updateRole(@PathVariable("id")int id,@RequestBody RoleReqDto roleReqDto);

    /**
     * 角色详情
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    @ApiOperation(value = "")
    ResponseDto<RoleRespDto> findRoleDetail(int id);

}
