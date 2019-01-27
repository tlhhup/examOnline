package org.tlh.exam.auth.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/23
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@ApiModel(value = "角色添加")
public class RoleReqDto implements Serializable {

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "角色值")
    private Integer roleValue = 0;

    @JsonProperty(value = "active")
    @ApiModelProperty(value = "是否可用")
    private Boolean isActive;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @JsonProperty(value = "pIds")
    @ApiModelProperty(value = "权限")
    private Integer[] permissionIds;

}
