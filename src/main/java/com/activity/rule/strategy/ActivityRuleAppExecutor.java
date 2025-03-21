package com.activity.rule.strategy;

import com.activity.rule.pojo.dto.ActivityRuleDTO;
import com.activity.rule.pojo.dto.RuleCheckResult;
import com.activity.rule.util.exception.BizException;
import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author maj
 * @title: ActivityRuleContainer
 *
 * @description: 负责执行活动中已配置的规则
 * @date 2023/7/611:30
 */

@Component
@Slf4j
public class ActivityRuleAppExecutor {

    private List<RuleAppStrategy> rules;

    @Autowired
    private ActivityAppRuleSelector activityRuleSelector;

    public void initializeRuleStrategies(List<String> ruleKeys) {
        /**
         * 加载所有已选的风控规则实现逻辑
         */
        this.rules = activityRuleSelector.getRuleStrategies(ruleKeys);
    }

    public RuleCheckResult execute(ActivityRuleDTO activityInfo, RuleCheckResult ruleCheckResult) throws BizException {

        validateRules();

        if (ruleCheckResult == null) {
            ruleCheckResult = new RuleCheckResult();
        }
        /**
         * 执行所有已加载的风控规则
         */
        for (RuleAppStrategy rule : rules) {
            rule.executeRule(activityInfo, ruleCheckResult);
            log.info("executeActivity : {} ,activityInfo:{} ,ruleCheckResult :{}  ", rule.getClass().getName(), JSON.toJSON(activityInfo), JSON.toJSON(ruleCheckResult));
            if (!ruleCheckResult.getIsPass()) {
                break;
            }
        }
        return ruleCheckResult;
    }


    public void validateRules() throws BizException {
        if (rules == null || rules.isEmpty()) {
            throw new BizException("No rules configured");
        }
    }

}
