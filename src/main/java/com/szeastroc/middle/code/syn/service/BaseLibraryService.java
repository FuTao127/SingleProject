package com.szeastroc.middle.code.syn.service;

import com.szeastroc.middle.code.syn.dto.ProductionCodeDTO;
import com.szeastroc.middle.code.syn.dto.SendCodeDetailDTO;
import com.szeastroc.middle.code.syn.dto.WarehousingDTO;
import com.szeastroc.middle.code.syn.model.ProductionCode;
import com.szeastroc.middle.code.syn.model.SendCodeDetail;
import com.szeastroc.middle.code.syn.model.Warehousing;

/**
 * @Description:基地库二维码service
 * @Author: futao
 * @Date: 2021/12/14
 **/
public interface BaseLibraryService {

    int importProductionCode(ProductionCode productionCode);

    int importSendCodeDetail(SendCodeDetail sendCodeDetail);

    int importWarehousing(Warehousing warehousing);

    int importBatchProductionCode(ProductionCodeDTO productionCodeDTO);

    int importBatchSendCodeDetail(SendCodeDetailDTO sendCodeDetailDTO);

    int importBatchWarehousing(WarehousingDTO warehousingDTO);
}
