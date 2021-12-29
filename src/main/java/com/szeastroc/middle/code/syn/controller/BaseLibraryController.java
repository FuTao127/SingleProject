package com.szeastroc.middle.code.syn.controller;


import com.szeastroc.middle.code.syn.annotation.LogSection;
import com.szeastroc.middle.code.syn.dto.ProductionCodeDTO;
import com.szeastroc.middle.code.syn.dto.SendCodeDetailDTO;
import com.szeastroc.middle.code.syn.dto.WarehousingDTO;
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

//    /**
//     * 生产码导入到基地库
//     * @return
//     */
//    @PostMapping("importProductionCode")
//    @ApiOperation("生产码导入到基地库")
//    @LogSection
//    public Result importProductionCode(@RequestBody ProductionCode productionCode){
//        loginService.verifyToken();
//        return ResultUtils.render(baseLibraryService.importProductionCode(productionCode));
//    }

    /**
     * 生产码批量导入到基地库
     * @return
     */
    @PostMapping("importBatchProductionCode")
    @ApiOperation("生产码批量导入到基地库")
    @LogSection
    public Result importBatchProductionCode(@RequestBody ProductionCodeDTO productionCodeDTO){
        loginService.verifyToken();
        return ResultUtils.render(baseLibraryService.importBatchProductionCode(productionCodeDTO));
    }


//    /**
//     * 发货详情信息入到基地库
//     * @return
//     */
//    @PostMapping("importSendCodeDetail")
//    @ApiOperation("发货详情信息入到基地库")
//    @LogSection
//    public Result importSendCodeDetail(@RequestBody SendCodeDetail sendCodeDetail){
//        loginService.verifyToken();
//        return ResultUtils.render(baseLibraryService.importSendCodeDetail(sendCodeDetail));
//    }


    /**
     * 发货详情信息批量导入到基地库
     * @return
     */
    @PostMapping("importBatchSendCodeDetail")
    @ApiOperation("发货详情信息批量导入到基地库")
    @LogSection
    public Result importBatchSendCodeDetail(@RequestBody SendCodeDetailDTO sendCodeDetailDTO){
        loginService.verifyToken();
        return ResultUtils.render(baseLibraryService.importBatchSendCodeDetail(sendCodeDetailDTO));
    }

//    /**
//     * 入库详情信息入基地库
//     * @param warehousing
//     * @return
//     */
//    @PostMapping("importWarehousing")
//    @ApiOperation("入库详情信息入基地库")
//    @LogSection
//    public Result importWarehousing(@RequestBody Warehousing warehousing){
//        loginService.verifyToken();
//        return ResultUtils.render(baseLibraryService.importWarehousing(warehousing));
//    }

    /**
     * 入库详情信息批量导入基地库
     * @param warehousingDTO
     * @return
     */
    @PostMapping("importBatchWarehousing")
    @ApiOperation("入库详情信息批量导入基地库")
    @LogSection
    public Result importBatchWarehousing(@RequestBody WarehousingDTO warehousingDTO){
        loginService.verifyToken();
        return ResultUtils.render(baseLibraryService.importBatchWarehousing(warehousingDTO));
    }
}
