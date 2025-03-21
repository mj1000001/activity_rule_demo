package com.activity.rule.strategy.impl;

import cn.hutool.core.lang.Assert;
import com.activity.rule.util.constant.CommonConstant;
import com.activity.rule.pojo.dto.ActivityRuleDTO;
import com.activity.rule.pojo.dto.RuleCheckResult;
import com.activity.rule.util.enums.ActivityRuleEnum;
import com.activity.rule.util.exception.BizException;
import com.activity.rule.strategy.RuleAppStrategy;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.activity.rule.util.constant.CommonConstant.FIFTH;

/**
 * @author maj
 * @title: MinParticipantsRule
 * 
 * @description: 活动的最低参与人数
 * @date 2023/7/610:23
 */

@Service
@Slf4j
public class MinParticipantsAppRule implements RuleAppStrategy {

    @Override
    public void executeRule(ActivityRuleDTO activityInfo, RuleCheckResult ruleCheckResult) throws BizException {
        Assert.isFalse(!activityInfo.getRuleMap().containsKey(ActivityRuleEnum.ENABLE_MIN_PARTICIPANTS.getCode()), "获取当前活动“最低参与人数开关”规则失败，请查看活动配置");
        //判断enable_min_participants 活动的最低参与人数校验是否开启 没开启，则返回
        String enableMinParticipants = Optional.ofNullable(activityInfo.getRuleMap().get(ActivityRuleEnum.ENABLE_MIN_PARTICIPANTS.getCode())).orElse("0");
        ruleCheckResult.setIsPass(true);
        if (Integer.parseInt(enableMinParticipants) == 0) {
            return;
        }

        //false未达最低限制
        boolean isMinParticipants = true;
        Assert.isFalse(!activityInfo.getRuleMap().containsKey(ActivityRuleEnum.MIN_PARTICIPANTS.getCode()), "获取当前活动“活动的最低参与人数”规则失败，请查看活动配置");
        //如果开启，则判断当前人数是否大于最低参与人数,如果小于则继续，否则满足条件
        String minParticipants = Optional.ofNullable(activityInfo.getRuleMap().get(ActivityRuleEnum.MIN_PARTICIPANTS.getCode())).orElse("-1");
        int minParticipantsNum = Integer.parseInt(minParticipants);

        //获取当前已助力的次数
        int count = getMinParticipants(activityInfo);
        log.info("activityInfo : {},count:{},minParticipantsNum:{}", JSON.toJSONString(activityInfo), count, minParticipantsNum);
        int currentCount = count + 1;
        if (currentCount < minParticipantsNum) {
            isMinParticipants = false;
        }
        //将计算出的参数放入map中，可以传递给下一个，用于两条关联性的风控逻辑校验
        ruleCheckResult.addInfo("isMinParticipants", isMinParticipants);
    }

    @Override
    public int getOrder() {
        return CommonConstant.THIRD;
    }

    @Override
    public String getRuleKey() {
        return ActivityRuleEnum.MIN_PARTICIPANTS.getCode();
    }

    /**
     * 获取当前已助力的次数
     *
     * @param activityInfo
     * @return
     */
    private int getMinParticipants(ActivityRuleDTO activityInfo) {
        //查询数据库获取当前已助力的次数
        return FIFTH;
    }

}
