package com.szeastroc.middle.code.syn.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Description:产线实时推送登陆
 * @Author: futao
 * @Date: 2021/12/14
 **/
@Data
public class LoginDTO {

    @NotEmpty(message = "userName不能为空")
    @ApiModelProperty(value = "登陆用户名")
    private String userName;

    @NotEmpty(message = "passWord不能为空")
    @ApiModelProperty(value = "登陆密码")
    private String passWord;

}
