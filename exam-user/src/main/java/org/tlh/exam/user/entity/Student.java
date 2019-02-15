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
@Table(name = "students")
public class Student implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String realName;
    private String national;
    private String email;
    private String tel;
    private String header;
    private int sex;
    private boolean isActive;

    @Column(updatable = false)
    private Date createTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToOne
    private Classes classes;

    @Column(nullable = false)
    private int authId;

}
