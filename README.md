# activity_rule_demo
活动规则demo
使用策略模式动态对活动规则进行管理，在新建活动时配置好规则后，在代码逻辑层即可加载活动选择的规则，并进行动态判断不需要再将每条规则逻辑都写死在活动代码里
``` java
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
```
