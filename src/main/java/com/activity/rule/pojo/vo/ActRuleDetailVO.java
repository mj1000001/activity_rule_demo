package com.activity.rule.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: lijinrong
 * @CreateTime: 2023-07-13  16:46
 * @Version: 1.0
 */
@Data
@ApiModel(value = "ActRuleDetailVO", description = "规则列表")
public class ActRuleDetailVO {
    @ApiModelProperty(value = "规则key")
    private String ruleKey;

    @ApiModelProperty(value = "规则名称")
    private String ruleName;

    @ApiModelProperty(value = "规则描述")
    private String ruleDesc;

    @ApiModelProperty(value = "活动类型：1：拼团，2：砍一刀")
    private Integer actType;

    @ApiModelProperty(value = "规则的值")
    private String ruleValue;

    @ApiModelProperty(value = "规则编码")
    private String ruleCode;
}
