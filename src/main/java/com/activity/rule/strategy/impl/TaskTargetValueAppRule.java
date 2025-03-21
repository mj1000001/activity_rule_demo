package com.activity.rule.strategy.impl;

import cn.hutool.core.lang.Assert;
import com.activity.rule.util.constant.ActRuleConstant;
import com.activity.rule.util.constant.CommonConstant;
import com.activity.rule.pojo.dto.ActivityRuleDTO;
import com.activity.rule.pojo.dto.RuleCheckResult;
import com.activity.rule.util.enums.ActivityRuleEnum;
import com.activity.rule.util.exception.BizException;
import com.alibaba.fastjson.JSON;
import com.activity.rule.strategy.RuleAppStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.activity.rule.util.constant.CommonConstant.ZEROS;

/**
 * @author maj
 * @title: TaskTargetValue
 * 
 * @description: 获取砍一刀任务目标值
 * @date 2023/7/611:17
 */
@Service
@Slf4j
public class TaskTargetValueAppRule implements RuleAppStrategy {

    @Override
    public void executeRule(ActivityRuleDTO activityInfo, RuleCheckResult ruleCheckResult) throws BizException {
        Assert.isFalse(!activityInfo.getRuleMap().containsKey(ActivityRuleEnum.TASK_TARGET_VALUE.getCode()), "获取当前活动“任务目标值”规则失败，请查看活动配置");
        Double targetValue = Double.parseDouble(activityInfo.getRuleMap().get(ActivityRuleEnum.TASK_TARGET_VALUE.getCode()));
        Double currentValue = getCurrentTargetValueCount(activityInfo);
        log.info("activityInfo : {},currentValue:{},maxTargetValue:{}", JSON.toJSONString(activityInfo), currentValue, targetValue);
        //当前任务值
        ruleCheckResult.addInfo(ActRuleConstant.CURRENT_TARGET_VALUE_COUNT, currentValue);
        ruleCheckResult.addInfo(ActRuleConstant.ACT_TARGET_VALUE, targetValue);
        //true未完成可以砍，false完成
        ruleCheckResult.setIsPass(true);
        if(currentValue >= targetValue){
            ruleCheckResult.setIsPass(false);
            ruleCheckResult.addInfo(ActRuleConstant.NOT_PASS_REASON,"活动目标已完成");
        }

    }

    @Override
    public int getOrder() {
        return CommonConstant.FIRST;
    }

    @Override
    public String getRuleKey() {
        return ActivityRuleEnum.TASK_TARGET_VALUE.getCode();
    }

    /**
     * 获取当前用户的任务值
     *
     * @param activityInfo
     * @return
     */
    private Double getCurrentTargetValueCount(ActivityRuleDTO activityInfo) {
        //查询数据库获取当前用户的任务值
        return ZEROS;
    }
}



