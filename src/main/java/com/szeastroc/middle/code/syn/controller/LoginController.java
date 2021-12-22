package com.szeastroc.middle.code.syn.controller;


import com.szeastroc.middle.code.syn.annotation.LogSection;
import com.szeastroc.middle.code.syn.dto.LoginDTO;
import com.szeastroc.middle.code.syn.service.LoginService;
import com.szeastroc.middle.code.syn.utils.Result;
import com.szeastroc.middle.code.syn.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:基地库用户导码登陆
 * @Author: futao
 * @Date: 2021/12/14
 **/
@RestController
@RequestMapping("middleCode")
@Slf4j
@Api(tags = "基地库用户导码登陆相关接口")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    @ApiOperation("基地库用户导码登陆")
    @LogSection
    public Result login(@RequestBody LoginDTO loginDTO) {
        try {
            return ResultUtils.render(loginService.login(loginDTO));
        } catch (Exception e) {
            log.info("login异常========>{}",e);
            return ResultUtils.renderError(e.getMessage());
        }

    }

}
