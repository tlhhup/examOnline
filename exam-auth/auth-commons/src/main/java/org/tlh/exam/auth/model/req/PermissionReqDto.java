package org.tlh.exam.auth.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/26
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@ApiModel(value = "权限实体")
public class PermissionReqDto implements Serializable{

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "请求地址")
    private String url;

    @ApiModelProperty(value = "权限简写",example = "user:edit")
    private String permission;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "是否为内部菜单")
    private Boolean iframe;

    @ApiModelProperty(value = "是否可用")
    private Boolean isActive;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "父级权限")
    private Integer parentId;

}
