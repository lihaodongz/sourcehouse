package com.ironman.sourcehouse.model;

import lombok.Data;
import java.util.Date;
import javax.persistence.*;

/**
 * 用户
 *
 * @author dazzlzy
 * @date 2018/5/19
 */
@Data
@Table(name = "user")
public class User {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private Integer status;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "last_login_time")
    private Date lastLoginTime;
    @Column(name = "last_update_time")
    private Date lastUpdateTime;
    @Column(name = "avatar")
    private String avatar;
}