package org.tlh.exam.user.model.resp;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/24
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@ApiModel(value = "用户信息")
public class AdminRespDto extends BaseDto{

    private int id;
    private String realName;
    private String userName;
    private int userType;
    private String national;
    private String email;
    private String tel;
    private String header;
    private int authId;

}
