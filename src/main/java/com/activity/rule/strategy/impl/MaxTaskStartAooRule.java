package com.activity.rule.strategy.impl;


import cn.hutool.core.lang.Assert;
import com.activity.rule.util.constant.ActRuleConstant;
import com.activity.rule.util.constant.CommonConstant;
import com.activity.rule.pojo.dto.ActivityRuleDTO;
import com.activity.rule.pojo.dto.RuleCheckResult;
import com.activity.rule.util.enums.ActivityRuleEnum;
import com.activity.rule.util.exception.BizException;
import com.activity.rule.strategy.RuleAppStrategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.activity.rule.util.constant.CommonConstant.FOURTH;

/**
 * @author maj
 * @title: MaxTaskHelpRule
 * 
 * @description: 当前活动发起次数规则
 * @date 2023/7/611:04
 */

@Service
@Slf4j
public class MaxTaskStartAooRule implements RuleAppStrategy {


    @Override
    public void executeRule(ActivityRuleDTO activityInfo, RuleCheckResult ruleCheckResult) throws BizException {
        Assert.isFalse(!activityInfo.getRuleMap().containsKey(ActivityRuleEnum.MAX_TASK_INITIATIONS.getCode()), "获取当前活动“活动可发起任务次数”规则失败，请查看活动配置");

        Double startCount = Double.valueOf(getActStartCount(activityInfo));
        Double maxTaskInitiations = Double.parseDouble(activityInfo.getRuleMap().get(ActivityRuleEnum.MAX_TASK_INITIATIONS.getCode()));
        log.info("startCount : {},maxTaskInitiations:{}", startCount, maxTaskInitiations);
        ruleCheckResult.setIsPass(true);
        if(startCount!=null && startCount >=maxTaskInitiations){
            ruleCheckResult.setIsPass(false);
            ruleCheckResult.addInfo(ActRuleConstant.NOT_PASS_REASON,"当前活动发起次数已达上限");
        }
    }

    @Override
    public int getOrder() {
        return CommonConstant.SECOND;
    }

    @Override
    public String getRuleKey() {
        return ActivityRuleEnum.MAX_TASK_INITIATIONS.getCode();
    }

    private Integer getActStartCount(ActivityRuleDTO activityInfo) throws BizException {
        String accountKey = activityInfo.getAccountKey();
        if(StringUtils.isEmpty(accountKey)){
            throw new BizException("当前用户未登陆！");
        }

        //查询数据库当前活动发起次数规则
        return FOURTH;
    }

}
