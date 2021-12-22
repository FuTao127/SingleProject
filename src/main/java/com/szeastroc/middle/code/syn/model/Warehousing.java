package com.szeastroc.middle.code.syn.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:入库详情信息
 * @Author: futao
 * @Date: 2021/12/20
 **/
@Data
public class Warehousing {
    @ApiModelProperty(value = "箱码")
    private String  boxCode;
    @ApiModelProperty(value = "'入库单号'")
    private String  inboundOrderNumber;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "'入库日期'")
    private Date warehousingDate;
    @ApiModelProperty(value = "'物料编码'")
    private String  materialCode;
    @ApiModelProperty(value = "'物料名称'")
    private String  materialName;
    @ApiModelProperty(value = "'批次号'")
    private String  batchNo;
    @ApiModelProperty(value = "'小批号'")
    private String  smallBatchNo;
    @ApiModelProperty(value = "'入仓仓库'")
    private String  inWarehouse;
    @ApiModelProperty(value = "'仓位'")
    private String  warehouse;
    @ApiModelProperty(value = "'仓库条码'")
    private String  warehouseCode;
    @ApiModelProperty(value = "'扫描人'")
    private String  scanPeople;
    @ApiModelProperty(value = "'扫码人编码'")
    private String  scanPeopleNo;
    @ApiModelProperty(value = "'扫描时间'")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date  scanDate;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "'生产日期'")
    private Date  productionDate;
    @ApiModelProperty(value = "'班组'")
    private String  team;
    @ApiModelProperty(value = "'生产线'")
    private String  productionLine;

}
