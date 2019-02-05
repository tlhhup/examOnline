package org.tlh.exam.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/5
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
public class ServiceInstanceDto implements Serializable{

    private String serviceId;
    private String host;
    private int port;
    private boolean isSecure;

}
