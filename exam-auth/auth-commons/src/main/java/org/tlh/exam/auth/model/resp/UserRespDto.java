package org.tlh.exam.auth.model.resp;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.tlh.exam.auth.model.UserDto;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/23
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@ApiModel(value = "用户实体")
public class UserRespDto extends UserDto {

    private int id;

}
