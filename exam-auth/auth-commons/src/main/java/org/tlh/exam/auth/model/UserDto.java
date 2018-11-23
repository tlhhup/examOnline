package org.tlh.exam.auth.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户添加实体")
public class UserDto extends UserResDto{

    @ApiModelProperty(value = "密码",required = true)
    private String password;

}
