package org.tlh.exam.auth.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.tlh.exam.auth.enums.UserType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/23
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@ApiModel("用户实体")
public class UserDto implements Serializable{

    @ApiModelProperty(value = "用户名",required = true)
    private String userName;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @ApiModelProperty(value = "用户类型",required = true)
    private UserType userType;

    @JsonProperty("active")
    @ApiModelProperty(value = "已激活")
    private boolean isActive=false;

    @ApiModelProperty(value = "添加时间",required = true)
    private Date createTime;

}
