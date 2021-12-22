package com.szeastroc.middle.code.syn.controller;


import com.szeastroc.middle.code.syn.annotation.LogSection;
import com.szeastroc.middle.code.syn.constants.EastrocException;
import com.szeastroc.middle.code.syn.model.ProductionCode;
import com.szeastroc.middle.code.syn.model.SendCodeDetail;
import com.szeastroc.middle.code.syn.model.Warehousing;
import com.szeastroc.middle.code.syn.service.BaseLibraryService;
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
 * @Description:基地库二维码controller
 * @Author: futao
 * @Date: 2021/12/14
 **/
@RestController
@RequestMapping("baseLibrary")
@Slf4j
@Api(tags = "基地库二维码导码相关接口")
public class BaseLibraryController {

    @Autowired
    private BaseLibraryService baseLibraryService;

    @Autowired
    private LoginService loginService;

    /**
     * 生产码导入到基地库
     * @return
     */
    @PostMapping("importProductionCode")
    @ApiOperation("生产码导入到基地库")
    @LogSection
    public Result importProductionCode(@RequestBody ProductionCode productionCode){
        try {
            loginService.verifyToken();
            return ResultUtils.render(baseLibraryService.importProductionCode(productionCode));
        }catch (EastrocException e){
            return ResultUtils.noLoginError(e.getMessage());
        }catch (Exception e){
            log.info("importProductionCode======================={}",e);
            return ResultUtils.renderError(e.getMessage());
        }
    }

    /**
     * 发货详情信息入到基地库
     * @return
     */
    @PostMapping("importSendCodeDetail")
    @ApiOperation("发货详情信息入到基地库")
    @LogSection
    public Result importSendCodeDetail(@RequestBody SendCodeDetail sendCodeDetail){
        try {
            loginService.verifyToken();
            return ResultUtils.render(baseLibraryService.importSendCodeDetail(sendCodeDetail));
        }catch (EastrocException e){
            return ResultUtils.noLoginError(e.getMessage());
        }catch (Exception e){
            log.info("importSendCodeDetail======================={}",e);
            return ResultUtils.renderError(e.getMessage());
        }
    }

    /**
     * 入库详情信息入基地库
     * @param warehousing
     * @return
     */
    @PostMapping("importWarehousing")
    @ApiOperation("入库详情信息入基地库")
    @LogSection
    public Result importWarehousing(@RequestBody Warehousing warehousing){
        try {
            loginService.verifyToken();
            return ResultUtils.render(baseLibraryService.importWarehousing(warehousing));
        }catch (EastrocException e){
            return ResultUtils.noLoginError(e.getMessage());
        }catch (Exception e){
            log.info("importWarehousing======================={}",e);
            return ResultUtils.renderError(e.getMessage());
        }
    }
}
