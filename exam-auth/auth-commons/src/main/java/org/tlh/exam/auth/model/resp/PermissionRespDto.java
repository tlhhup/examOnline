package org.tlh.exam.auth.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.tlh.exam.auth.model.req.PermissionReqDto;

import java.util.List;

/**
 * Created by 离歌笑tlh/hu ping on 2018/11/26
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@ApiModel(value = "权限响应实体")
public class PermissionRespDto extends PermissionReqDto {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "创建人")
    private String creator;

    @ApiModelProperty(value = "子菜单")
    private List<PermissionRespDto> children;
}
