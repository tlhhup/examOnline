package org.tlh.exam.user.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.tlh.exam.user.model.BaseDto;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/24
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
public class AdminReqDto extends BaseDto{

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "籍贯")
    private String national;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话")
    private String tel;

}
