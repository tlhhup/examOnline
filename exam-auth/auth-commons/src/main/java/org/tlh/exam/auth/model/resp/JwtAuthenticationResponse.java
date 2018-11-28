package org.tlh.exam.auth.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/28
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@AllArgsConstructor
@ApiModel(value = "用户认证token")
public class JwtAuthenticationResponse implements Serializable{

    @ApiModelProperty(value = "token")
    private String token;

}
