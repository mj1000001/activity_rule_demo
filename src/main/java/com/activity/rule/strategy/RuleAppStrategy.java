package com.activity.rule.strategy;


import com.activity.rule.pojo.dto.ActivityRuleDTO;
import com.activity.rule.pojo.dto.RuleCheckResult;
import com.activity.rule.util.exception.BizException;

/**
 * @author maj
 * @title: RuleStrategy
 * 
 * @description: 活动规则的策略接口
 * @date 2023/7/610:09
 */
public interface RuleAppStrategy {

    /**
     * 规则检查逻辑
     * @param activityInfo
     * @param ruleCheckResult
     * @return
     * @throws BizException
     */
    void executeRule(ActivityRuleDTO activityInfo, RuleCheckResult ruleCheckResult) throws BizException;

    /**
     *获取规则优先级的方法
     * @return
     */
    int getOrder();

    /**
     * 规则key
     * @return
     */
    String getRuleKey();
}
