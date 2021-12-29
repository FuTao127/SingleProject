package com.szeastroc.middle.code.syn.service.impl;

import com.szeastroc.middle.code.syn.constants.EastrocException;
import com.szeastroc.middle.code.syn.constants.TableName;
import com.szeastroc.middle.code.syn.dto.ProductionCodeDTO;
import com.szeastroc.middle.code.syn.dto.SendCodeDetailDTO;
import com.szeastroc.middle.code.syn.dto.WarehousingDTO;
import com.szeastroc.middle.code.syn.mapper.BaseLibraryMapper;
import com.szeastroc.middle.code.syn.model.ProductionCode;
import com.szeastroc.middle.code.syn.model.SendCodeDetail;
import com.szeastroc.middle.code.syn.model.Warehousing;
import com.szeastroc.middle.code.syn.mq.sender.ProductionCodeSender;
import com.szeastroc.middle.code.syn.mq.sender.SendCodeDetailSender;
import com.szeastroc.middle.code.syn.service.BaseLibraryService;
import com.szeastroc.middle.code.syn.utils.ValidationUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    @Transactional
    public int importBatchProductionCode(ProductionCodeDTO productionCodeDTO) {
        List<ProductionCode> productionCodes = productionCodeDTO.getProductionCodes();
        ValidationUtils.validate(productionCodeDTO);
        validIsImport(productionCodeDTO.getId());
        validSize(productionCodes);
        String tableName = TableName.T_MIDDLE_CODE+new DateTime().toString("yyyyMMdd");
        int count = baseLibraryMapper.importBatchProductionCode(tableName, productionCodes,productionCodeDTO.getId());
        for(ProductionCode productionCode : productionCodes){
        productionCodeSender.sendData(productionCode);
        }
        return count;
    }

    @Override
    @Transactional
    public int importBatchSendCodeDetail(SendCodeDetailDTO sendCodeDetailDTO) {
        List<SendCodeDetail> sendCodeDetails = sendCodeDetailDTO.getSendCodeDetails();
        ValidationUtils.validate(sendCodeDetailDTO);
        validIsImport(sendCodeDetailDTO.getId());
        validSize(sendCodeDetails);
        String tableName = TableName.T_MIDDLE_SEND_DETAIL+new DateTime().toString("yyyyMMdd");
        int count = baseLibraryMapper.importBatchSendCodeDetail(tableName,sendCodeDetails,sendCodeDetailDTO.getId());
        for(SendCodeDetail sendCodeDetail : sendCodeDetails){
            sendCodeDetailSender.sendData(sendCodeDetail);
        }
        return  count;
    }

    @Override
    @Transactional
    public int importBatchWarehousing(WarehousingDTO warehousingDTO) {
        List<Warehousing> warehousings = warehousingDTO.getWarehousings();
        ValidationUtils.validate(warehousingDTO);
        validIsImport(warehousingDTO.getId());
        validSize(warehousings);
        String tableName = TableName.T_MIDDLE_WAREHOUSING+new DateTime().toString("yyyyMMdd");
        return  baseLibraryMapper.importBatchWarehousing(tableName,warehousings,warehousingDTO.getId());
    }

    /**
     * 校验数据是否导入过
     * @param task_id
     */
    private void validIsImport(String task_id) {
        int count = baseLibraryMapper.insertTaskId(task_id);
        if(count == 0  ){
            throw new EastrocException("402","您已经导入过该数据！");
        }
    }



    /**
     * 校验集合最大长度
     * @param list
     */
    private void validSize(List list) {
        if(list == null){
            throw new EastrocException("导入数据不能为空！");
        }
        if(list.size() > 200){
            throw new EastrocException("数据最大导入条数为200条，您导入的长度超长！");
        }
        if(list.size() <= 0 ){
            throw new EastrocException("您导入条数为0条，请传入数据！");
        }
    }


}
