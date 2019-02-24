package org.tlh.exam.user.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/24
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@ApiModel(value = "添加管理员")
public class AdminAddDto extends AdminReqDto{

    @ApiModelProperty(value = "密码")
    private String password;

}
