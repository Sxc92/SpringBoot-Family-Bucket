package com.christ.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 史偕成
 * @date 2023/08/04 17:02
 **/
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;


    private String name;

    @Column(name = "nameZh")
    private String nameZh;
}
