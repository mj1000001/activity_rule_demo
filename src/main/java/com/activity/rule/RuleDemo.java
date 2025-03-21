package com.activity.rule;

import cn.hutool.core.util.ObjectUtil;
import com.activity.rule.util.constant.ActRuleConstant;
import com.activity.rule.pojo.dto.ActivityRuleDTO;
import com.activity.rule.pojo.dto.RuleCheckResult;
import com.activity.rule.util.enums.ActivityRuleEnum;
import com.activity.rule.util.exception.BizException;
import com.activity.rule.strategy.ActivityRuleAppExecutor;
import com.activity.rule.service.ActivityRuleService;
import com.activity.rule.pojo.vo.ActRuleDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author maj
 * @version 1.0
 * @description:  demo 测试类
 * @date 2025/3/20 17:17
 */

@Slf4j
public class RuleDemo {

    @Autowired
    private ActivityRuleAppExecutor activityRuleExecutor;

    @Autowired
    private ActivityRuleService activityRuleService;

    public void testRule() throws BizException {
        String actKey = "actKey";
        String participantAccountKey = "participantAccountKey";
        String taskKey = "taskKey";
        String accountKey = "accountKey";
        //执行风控规则
        RuleCheckResult checkResult = initAndExecuteRule(actKey, participantAccountKey, taskKey, accountKey);
        // 若检查未通过，则抛出异常
        if (!checkResult.getIsPass()) {
            System.out.println(ObjectUtil.toString(checkResult.getInfo(ActRuleConstant.NOT_PASS_REASON)));
        }
    }


    public RuleCheckResult initAndExecuteRule(String actKey, String participantKey, String taskKey, String accountKey) throws BizException {
        List<ActRuleDetailVO> ruleDetailVOS = activityRuleService.findRuleDetail(actKey, null);
        //加载数据库中活动已选择的风控规则, 参数格式参考 ruleKeys = getRuleKeys();
        List<String> ruleKeys =  ruleDetailVOS.stream().map(ActRuleDetailVO::getRuleCode).collect(Collectors.toList());
        // 获取到活动已选择的风控规则后，初始化风控规则
        activityRuleExecutor.initializeRuleStrategies(ruleKeys);
        //加载数据库中活动对应的规则配置的值
        Map<String, String> ruleMap = ruleDetailVOS.stream().collect(Collectors.toMap(ActRuleDetailVO::getRuleCode, ActRuleDetailVO::getRuleValue));
        RuleCheckResult ruleCheckResult = new RuleCheckResult();
        activityRuleExecutor.execute(ActivityRuleDTO.builder().participantKey(participantKey).activityKey(actKey)
                .taskKey(taskKey).accountKey(accountKey).ruleMap(ruleMap).build(), ruleCheckResult);
        return ruleCheckResult;
    }

    private List<String> getRuleKeys() {
        List<String> ruleKeys = new ArrayList<>();
        ruleKeys.add(ActivityRuleEnum.MAX_TASK_HELP.getCode());
        ruleKeys.add(ActivityRuleEnum.TASK_TARGET_VALUE.getCode());
        ruleKeys.add(ActivityRuleEnum.MAX_CUT_PERCENTAGE.getCode());
        return ruleKeys;
    }


}
