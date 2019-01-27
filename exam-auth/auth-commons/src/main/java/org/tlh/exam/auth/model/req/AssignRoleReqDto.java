package org.tlh.exam.auth.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by 离歌笑tlh/hu ping on 2019/1/27
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@ApiModel(value = "分配角色")
public class AssignRoleReqDto implements Serializable{

    @JsonProperty(value = "uId")
    @ApiModelProperty(value = "用户ID")
    private int id;

    @JsonProperty(value = "rIds")
    @ApiModelProperty(value = "角色ID")
    private int[] roleIds;

}
