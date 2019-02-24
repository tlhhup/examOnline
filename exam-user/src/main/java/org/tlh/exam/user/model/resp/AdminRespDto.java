package org.tlh.exam.user.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.tlh.exam.user.model.req.AdminReqDto;

import java.util.Date;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/24
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@ApiModel(value = "用户信息")
public class AdminRespDto extends AdminReqDto {

    @ApiModelProperty(value = "用户ID")
    private int id;

    @ApiModelProperty(value = "用户类型")
    private int userType;

    @ApiModelProperty("用户头像")
    private String header;

    @ApiModelProperty(value = "认证ID")
    private int authId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
