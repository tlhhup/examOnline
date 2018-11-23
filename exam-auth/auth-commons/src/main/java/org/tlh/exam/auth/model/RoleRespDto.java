package org.tlh.exam.auth.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/23
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@ApiModel("角色返回实体")
public class RoleRespDto extends RoleReqDto{

    private int id;

    @ApiModelProperty(value = "创建人")
    private String creator;

}
