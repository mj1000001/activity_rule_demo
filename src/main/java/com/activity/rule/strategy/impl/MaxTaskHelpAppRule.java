package com.activity.rule.strategy.impl;

import cn.hutool.core.lang.Assert;
import com.activity.rule.util.constant.ActRuleConstant;
import com.activity.rule.util.constant.CommonConstant;
import com.activity.rule.pojo.dto.ActivityRuleDTO;
import com.activity.rule.pojo.dto.RuleCheckResult;
import com.activity.rule.util.enums.ActivityRuleEnum;
import com.activity.rule.util.exception.BizException;
import com.activity.rule.strategy.RuleAppStrategy;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.activity.rule.util.constant.CommonConstant.FIRST;

/**
 * @author maj
 * @title: MaxTaskHelpRule
 * 
 * @description: 当前活动助力次数规则
 * @date 2023/7/611:04
 */

@Service
@Slf4j
public class MaxTaskHelpAppRule implements RuleAppStrategy {


    @Override
    public void executeRule(ActivityRuleDTO activityInfo, RuleCheckResult ruleCheckResult) throws BizException {
        Assert.isFalse(!activityInfo.getRuleMap().containsKey(ActivityRuleEnum.MAX_TASK_HELP.getCode()), "获取当前活动“助力次数规则”规则失败，请查看活动配置");
        Double maxTaskHelp = Double.parseDouble(activityInfo.getRuleMap().get(ActivityRuleEnum.MAX_TASK_HELP.getCode()));
        Integer count = getParticipationNum(activityInfo);
        ruleCheckResult.setIsPass(true);
        log.info("activityInfo : {},count:{},maxTaskHelp:{}", JSON.toJSONString(activityInfo), count, maxTaskHelp);
        if(count >= maxTaskHelp){
            ruleCheckResult.setIsPass(false);
            ruleCheckResult.addInfo(ActRuleConstant.NOT_PASS_REASON,"活动助力次数已达上限");
        }
    }

    @Override
    public int getOrder() {
        return CommonConstant.SECOND;
    }

    @Override
    public String getRuleKey() {
        return ActivityRuleEnum.MAX_TASK_HELP.getCode();
    }

    /**
     * 获取当前参与人的助力次数
     *
     * @param activityInfo
     * @return
     */
    private int getParticipationNum(ActivityRuleDTO activityInfo) {
        //查询数据库获取当前参与人的助力次数
        return FIRST;
    }
}
