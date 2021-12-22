package com.szeastroc.middle.code.syn.controller;

import com.szeastroc.middle.code.syn.utils.Result;
import com.szeastroc.middle.code.syn.utils.ResultUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:心跳检查接口
 * @Author: futao
 * @Date: 2021/12/20
 **/
@RestController
@RequestMapping("/otoc-realtime-syn")
public class HeartBeatController {
    @RequestMapping("heartBeat")
    public Result heartBeat(){
        return ResultUtils.render();
    }
}
