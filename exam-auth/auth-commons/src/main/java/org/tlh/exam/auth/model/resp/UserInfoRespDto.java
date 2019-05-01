package org.tlh.exam.auth.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/11
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@ApiModel(value = "用户信息")
public class UserInfoRespDto implements Serializable{

    @ApiModelProperty(value = "用户ID")
    private int id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "角色")
    private Set<String> roles;

    @ApiModelProperty(value = "权限")
    private Set<String> permissions;

}
