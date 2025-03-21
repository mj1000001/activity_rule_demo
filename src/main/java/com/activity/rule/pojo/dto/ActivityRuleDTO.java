package com.activity.rule.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Map;

/**
 * @author maj
 * @title: ActivityRuleDto
 * 
 * @description: 风控规则参数DTO，是根据每个规则需要的参数提取出来的DTO，主要用来传入到对应的规则中进行逻辑校验和计算
 * @date 2023/7/610:43
 */

@ApiModel(value = "ActivityRuleDto", description = "活动规则参数DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ActivityRuleDTO {

    @ApiModelProperty(value = "活动key")
    private String activityKey;

    @ApiModelProperty(value = "任务key")
    private String taskKey;

    @ApiModelProperty(value = "发起人账号")
    private String accountKey;

    @ApiModelProperty(value = "参与人账号")
    private String participantKey;

    @ApiModelProperty(value = "当前活动配置的规则值")
    Map<String, String> ruleMap;

}
