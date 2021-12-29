package com.szeastroc.middle.code.syn.dto;

import com.szeastroc.middle.code.syn.model.Warehousing;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Description:
 * @Author: futao
 * @Date: 2021/12/24
 **/
@Data
public class WarehousingDTO {
    private List<Warehousing> warehousings;
    @NotEmpty(message = "任务id不能为空")
    @ApiModelProperty(value = "任务唯一id")
    private String id;
}
