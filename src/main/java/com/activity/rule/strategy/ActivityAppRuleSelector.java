package com.activity.rule.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author maj
 * @title: RuleFactory
 *
 * @description: 负责初始化加载对应顺序执行的活动规则
 * @date 2023/7/611:33
 */


@Component
@Slf4j
public class ActivityAppRuleSelector {

    /**
     * 风控规则的实现列表，key为规则的key，value为风控的规则的逻辑实现，按照规则的优先级进行加载
     *
     */
    private Map<String, RuleAppStrategy> ruleStrategies;

    @Autowired
    public ActivityAppRuleSelector(List<RuleAppStrategy> ruleStrategies) {
        this.ruleStrategies = ruleStrategies.stream()
                .collect(Collectors.toMap(RuleAppStrategy::getRuleKey, Function.identity(),
                        (existing, replacement) -> { // 合并重复 Key 的处理逻辑
                    log.warn("Duplicate rule key: {}", existing.getRuleKey());
                    return existing; // 保留现有策略或替换为新策略
                }));
    }

    public List<RuleAppStrategy> getRuleStrategies(List<String> ruleKeys) {
        return ruleKeys.stream()
                .map(ruleStrategies::get)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(RuleAppStrategy::getOrder))
                .collect(Collectors.toList());
    }
}
