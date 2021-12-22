package com.szeastroc.middle.code.syn.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:生产码
 * @Author: futao
 * @Date: 2021/12/14
 **/
@Data
public class ProductionCode {
    @ApiModelProperty(value = "外箱码")
    private String boxOutCode;
    @ApiModelProperty(value = "内箱码")
    private String boxInCode;
    @ApiModelProperty(value = "瓶码")
    private String outCode;
    @ApiModelProperty(value = "数量")
    private Integer amount;
    @ApiModelProperty(value = "生产时间")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date productTime;
    @ApiModelProperty(value = "采集时间")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date collectTime;
    @ApiModelProperty(value = "生产线编码")
    private String productionLineCode;
    @ApiModelProperty(value = "工厂编码")
    private String factoryCode;
    @ApiModelProperty(value = "产品编码")
    private String productCode;
    @ApiModelProperty(value = "班组名称")
    private String teamName;
    @ApiModelProperty(value = "生产批次")
    private String batchNo;
    @ApiModelProperty(value = "'小批号'")
    private String smallBatchNo;
    @ApiModelProperty(value = "'入库状态'")
    private Integer status;

}
