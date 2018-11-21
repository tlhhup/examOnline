package org.tlh.exam.auth.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "auth_permission")
public class Permission extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String url;
    private Integer sort;
    private Boolean isActive;
    private Date createTime;
    private Date updateTime;

    @ManyToOne
    @JoinColumn(name = "pid")
    private Permission permission;

}
