package com.szeastroc.middle.code.syn.service.impl;

import com.szeastroc.middle.code.syn.constants.TableName;
import com.szeastroc.middle.code.syn.mapper.BaseLibraryMapper;
import com.szeastroc.middle.code.syn.model.ProductionCode;
import com.szeastroc.middle.code.syn.model.SendCodeDetail;
import com.szeastroc.middle.code.syn.model.Warehousing;
import com.szeastroc.middle.code.syn.mq.sender.ProductionCodeSender;
import com.szeastroc.middle.code.syn.mq.sender.SendCodeDetailSender;
import com.szeastroc.middle.code.syn.service.BaseLibraryService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:基地库二维码impl
 * @Author: futao
 * @Date: 2021/12/14
 **/
@Service
public class BaseLibraryServiceImpl implements BaseLibraryService {

    @Autowired
    private ProductionCodeSender productionCodeSender;

    @Autowired
    private SendCodeDetailSender sendCodeDetailSender;

    @Autowired
    private BaseLibraryMapper baseLibraryMapper;



    @Override
    public int importProductionCode(ProductionCode productionCode) {
        String tableName = TableName.T_MIDDLE_CODE+new DateTime().toString("yyyyMMdd");
        productionCodeSender.sendData(productionCode);
        return  baseLibraryMapper.importProductionCode(tableName,productionCode);
    }

    @Override
    public int importSendCodeDetail(SendCodeDetail sendCodeDetail) {
        String tableName = TableName.T_MIDDLE_SEND_DETAIL+new DateTime().toString("yyyyMMdd");
        sendCodeDetailSender.sendData(sendCodeDetail);
        return  baseLibraryMapper.importSendCodeDetail(tableName,sendCodeDetail);
    }

    @Override
    public int importWarehousing(Warehousing warehousing) {
        String tableName = TableName.T_MIDDLE_WAREHOUSING+new DateTime().toString("yyyyMMdd");
        return  baseLibraryMapper.importWarehousing(tableName,warehousing);
    }
}
