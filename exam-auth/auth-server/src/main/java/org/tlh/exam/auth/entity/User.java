package org.tlh.exam.auth.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "auth_users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;
    private String password;
    private Integer userType;
    private Boolean isActive;
    private Date createTime;
    private Date updateTime;

    @ManyToMany(targetEntity = Role.class)
    private List<Role> roles;

}
