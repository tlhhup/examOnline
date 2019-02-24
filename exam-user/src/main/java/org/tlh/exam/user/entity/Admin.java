package org.tlh.exam.user.entity;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/6
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@Entity
@Table(name = "sys_admin")
public class Admin implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String realName;
    private String national;
    private String email;
    private String tel;
    private String header;

    @Column(updatable = false)
    private Integer userType;

    private Boolean isActive;

    @Column(updatable = false)
    private Date createTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Column(nullable = false,updatable = false)
    private Integer authId;

}
