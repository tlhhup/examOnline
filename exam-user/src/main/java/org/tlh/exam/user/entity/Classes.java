package org.tlh.exam.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/6
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@Entity
@Table(name = "sys_classes")
public class Classes implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;

}
