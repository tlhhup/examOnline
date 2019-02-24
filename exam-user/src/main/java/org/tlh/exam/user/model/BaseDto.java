package org.tlh.exam.user.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/24
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
public class BaseDto implements Serializable{

    @ApiModelProperty(value = "是否可用")
    private boolean isActive;

}
