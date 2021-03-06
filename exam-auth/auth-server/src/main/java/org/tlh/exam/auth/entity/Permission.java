package org.tlh.exam.auth.entity;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

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
    private String permission;
    private Integer sort;
    private Boolean isActive;
    private String icon;
    private Boolean iframe;//内部菜单，按钮

    @Column(updatable = false)
    private Date createTime;
    private String creator;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToOne
    @JoinColumn(name = "pid")
    private Permission parent;

}
