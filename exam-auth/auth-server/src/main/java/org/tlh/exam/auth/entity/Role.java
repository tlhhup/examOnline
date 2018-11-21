package org.tlh.exam.auth.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "auth_roles")
public class Role extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roleName;
    private String description;
    private Integer roleValue;
    private Boolean isActive;
    private Date createTime;
    private Date updateTime;

    @ManyToMany
    private List<Permission> permissions;

}
