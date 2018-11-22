package org.tlh.exam.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.tlh.exam.auth.enums.UserType;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("用户添加实体")
public class UserResDto implements Serializable{

    @JsonProperty("username")
    @ApiModelProperty(value = "用户名",required = true)
    private String userName;

    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @JsonProperty("user_type")
    @ApiModelProperty(value = "用户类型",required = true)
    private UserType userType;

    @JsonProperty("active")
    @ApiModelProperty(value = "已激活")
    private boolean isActive=false;

    @JsonProperty("create_time")
    @ApiModelProperty(value = "添加时间",required = true)
    private Date createTime;

}
