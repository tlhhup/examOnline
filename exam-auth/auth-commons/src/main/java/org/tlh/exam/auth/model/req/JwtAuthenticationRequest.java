package org.tlh.exam.auth.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.tlh.exam.auth.enums.UserType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/28
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@ApiModel(value = "用户认证")
public class JwtAuthenticationRequest implements Serializable{

    @NotNull
    @ApiModelProperty(value = "用户名")
    private String userName;

    @NotNull
    @ApiModelProperty(value = "密码")
    private String password;

    @NotNull
    @ApiModelProperty(value = "用户类型")
    private UserType userType;

}
