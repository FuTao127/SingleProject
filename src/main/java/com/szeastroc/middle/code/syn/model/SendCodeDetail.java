package com.szeastroc.middle.code.syn.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:发货详情信息
 * @Author: futao
 * @Date: 2021/12/14
 **/
@Data
public class SendCodeDetail {
    @ApiModelProperty(value = "外箱码")
    private String boxOutCode;
    @ApiModelProperty(value = "工厂编码")
    private String factoryCode;
    @ApiModelProperty(value = "经销商编码")
    private String agentCode;
    @ApiModelProperty(value = "产品编码")
    private String productCode;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "出货日期")
    private Date outStockDate;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "扫描时间")
    private Date scanTime;
    @ApiModelProperty(value = "批次号")
    private String batchNo;

    @ApiModelProperty(value = "'小批号'")
    private String smallBatchNo;
    @ApiModelProperty(value = "'单号'")
    private String orderNo;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "'创建时间'")
    private Date createTime;
    @ApiModelProperty(value = "'审核日期'")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date auditTime;
    @ApiModelProperty(value = "'发货日期'")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date sendGoodsTime;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "'单据日期")
    private Date docTime;

}
