package com.szeastroc.middle.code.syn.model;

import lombok.Data;

import java.util.Date;

/**
 * @Description:产线实时导码用户信息
 * @Author: futao
 * @Date: 2021/12/14
 **/
@Data
public class Userinfo {
    private String userId;
    private String salt;
    private String userName;
    private String passWord;
    private Date lastLoginDate;
    private Date createDate;
    private Integer status;
}
