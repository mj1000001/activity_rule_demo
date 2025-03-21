package com.activity.rule.strategy.impl;


import cn.hutool.core.lang.Assert;
import com.activity.rule.pojo.dto.ActivityRuleDTO;
import com.activity.rule.pojo.dto.RuleCheckResult;
import com.activity.rule.strategy.RuleAppStrategy;
import com.activity.rule.util.constant.ActRuleConstant;
import com.activity.rule.util.constant.CommonConstant;
import com.activity.rule.util.enums.ActivityRuleEnum;
import com.activity.rule.util.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author maj
 * @title: MaxTaskHelpRule
 * @description: 获取助力百分比
 * @date 2023/7/611:04
 */

@Service
@Slf4j
public class CutPercentageAppRule implements RuleAppStrategy {
    @Override
    public void executeRule(ActivityRuleDTO activityInfo, RuleCheckResult ruleCheckResult) throws BizException {
        Assert.isFalse(!activityInfo.getRuleMap().containsKey(ActivityRuleEnum.MAX_CUT_PERCENTAGE.getCode()), "获取当前活动“砍一刀最大值”规则失败，请查看活动配置");
        float maxCutPercentage = Float.parseFloat(activityInfo.getRuleMap().get(ActivityRuleEnum.MAX_CUT_PERCENTAGE.getCode()));
        Float minCutPercentage = 0f;
        if (activityInfo.getRuleMap().containsKey(ActivityRuleEnum.MIN_CUT_PERCENTAGE.getCode())) {
            minCutPercentage = Float.parseFloat(activityInfo.getRuleMap().get(ActivityRuleEnum.MIN_CUT_PERCENTAGE.getCode()));
        }

        boolean isMinParticipants = Optional.ofNullable(Boolean.parseBoolean(ruleCheckResult.getInfo("isMinParticipants").toString())).orElse(true);
        if (!isMinParticipants) {
            // TODO 校验最低人数,如果当前参与人数没达到最低数，则不能成功，随机数不能大于100

        }

        minCutPercentage = minCutPercentage * 100;
        maxCutPercentage = maxCutPercentage * 100;
        //开始生成随机数
        float random = minCutPercentage + (int) (Math.random() * (maxCutPercentage - minCutPercentage));
        ruleCheckResult.addInfo(ActRuleConstant.RANDOM, new BigDecimal(random).divide(new BigDecimal(100)).toString());
        ruleCheckResult.setIsPass(true);
    }

    @Override
    public int getOrder() {
        return CommonConstant.FOURTH;
    }

    @Override
    public String getRuleKey() {
        return ActivityRuleEnum.MAX_CUT_PERCENTAGE.getCode();
    }


}
