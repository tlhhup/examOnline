package org.tlh.exam.auth.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Created by 离歌笑tlh/hu ping on 2019/1/13
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@ApiModel(value = "修改密码实体")
public class ChangePwdReqDto implements Serializable{

    @ApiModelProperty(value = "id")
    private int id;

    @NotEmpty
    @ApiModelProperty(value = "密码")
    private String password;

}
