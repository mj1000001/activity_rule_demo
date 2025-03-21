package com.activity.rule.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maj
 * @title: RuleCheckResult
 * 
 * @description: 活动规则校验返回参数
 * @date 2023/7/616:32
 */


@ApiModel(value = "RuleCheckResult", description = "活动规则校验返回参数")
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RuleCheckResult {

    @ApiModelProperty(value = "校验是否通过")
    private Boolean isPass;


    @ApiModelProperty(value = "额外补充的map")
    private Map<String, Object> extraInfo;

    /**
     * 存储风控规则的错误信息，或者放入上一条风控所计算出的信息，传递给下一个风控，用于两条关联性的风控逻辑校验
     */
    public void addInfo(String key, Object value) {
        extraInfo.put(key, value);
    }

    public Object getInfo(String key) {
        return extraInfo.get(key);
    }


    public RuleCheckResult() {
        extraInfo = new HashMap<String, Object>();
    }

    private Map<String, Object> getExtraInfo() {
        return extraInfo;
    }

    private void setExtraInfo(Map<String, Object> extraInfo) {
        this.extraInfo = extraInfo;
    }

}
