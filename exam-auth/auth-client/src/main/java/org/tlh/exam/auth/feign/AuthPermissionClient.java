package org.tlh.exam.auth.feign;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.tlh.exam.auth.fallback.AuthPermissionClientFallback;
import org.tlh.exam.auth.model.req.PermissionReqDto;
import org.tlh.exam.auth.model.resp.PermissionRespDto;
import org.tlh.exam.auth.model.resp.ResponseDto;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/23
 * <p>
 * Github: https://github.com/tlhhup
 */
@FeignClient(name = "exam-auth-server", path = "/auth/authRole",fallback = AuthPermissionClientFallback.class)
public interface AuthPermissionClient {

    /**
     * 添加权限
     *
     * @param permissionReqDto
     * @return
     */
    @PostMapping("/create")
    @ApiOperation(value = "")
    ResponseDto<Boolean> createPermission(@RequestBody PermissionReqDto permissionReqDto);

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "")
    ResponseDto<Boolean> deletePermission(@PathVariable("id") int id);

    /**
     * 禁用(启用)权限
     *
     * @param id
     * @param active
     * @return
     */
    @PostMapping("/modify/{id}/active")
    ResponseDto<Boolean> permissionActive(@PathVariable("id") int id,
                                             @RequestParam("active") boolean active);

    /**
     * 更新权限
     *
     * @param id
     * @param permissionReqDto
     * @return
     */
    @PostMapping("/modify/{id}")
    ResponseDto<Boolean> permissionUpdate(@PathVariable("id") int id,
                                             @RequestBody PermissionReqDto permissionReqDto);

    /**
     * 权限详情
     *
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    ResponseDto<PermissionRespDto> permissionDetail(@PathVariable("id") int id);

}
