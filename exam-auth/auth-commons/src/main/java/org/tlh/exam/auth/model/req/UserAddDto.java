package org.tlh.exam.auth.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.tlh.exam.auth.model.UserDto;

@Data
@ApiModel("用户添加实体")
public class UserAddDto extends UserDto {

    @ApiModelProperty(value = "密码",required = true)
    private String password;

}
