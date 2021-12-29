package com.szeastroc.middle.code.syn.mapper;

import com.szeastroc.middle.code.syn.model.ProductionCode;
import com.szeastroc.middle.code.syn.model.SendCodeDetail;
import com.szeastroc.middle.code.syn.model.Warehousing;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:基地库二维码mapper
 * @Author: futao
 * @Date: 2021/12/14
 **/
@Repository
public interface BaseLibraryMapper {

    int importProductionCode(@Param("tableName") String tableName,@Param("productionCode") ProductionCode productionCode);

    int importSendCodeDetail(@Param("tableName") String tableName,@Param("sendCodeDetail") SendCodeDetail sendCodeDetail);

    int importWarehousing(@Param("tableName") String tableName,@Param("warehousing") Warehousing warehousing);

    int importBatchProductionCode(@Param("tableName") String tableName,@Param("productionCodes")  List<ProductionCode> productionCodes,@Param("taskId")  String taskId);

    int importBatchSendCodeDetail(@Param("tableName") String tableName,@Param("sendCodeDetails")  List<SendCodeDetail> sendCodeDetails,@Param("taskId")  String taskId);

    int importBatchWarehousing(@Param("tableName") String tableName,@Param("warehousings")  List<Warehousing> warehousings,@Param("taskId")  String taskId);

    int insertTaskId(@Param("id") String id);
}
