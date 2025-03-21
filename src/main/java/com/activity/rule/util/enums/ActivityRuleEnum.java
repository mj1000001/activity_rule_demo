package com.activity.rule.util.enums;

import lombok.Getter;

/**
 * @author maj
 * @title: ActivityRuleEnum
 *
 * @description: 活动规则枚举
 * @date 2023/7/614:49
 */
@Getter
public enum ActivityRuleEnum {
    /**
     * ActivityRuleEnum 中的数据和数据库中的规则编码一一对应，每次有新增规则是，表中增加，枚举也要增加，
     * 因为既然是新增风控规则，那么对应的逻辑肯定要重写，所以在这边新增对应的枚举和增加对应的风控规则逻辑
     */
    ENABLE_MIN_PARTICIPANTS("enable_min_participants", "活动的最低参与人数开关"),
    MIN_PARTICIPANTS("min_participants", "活动的最低参与人数"),
    MAX_TASK_INITIATIONS("max_task_initiations", "活动可发起任务次数"),
    MAX_CUT_PERCENTAGE("max_cut_percentage", "砍一刀最大值"),
    MIN_CUT_PERCENTAGE("min_cut_percentage", "砍一刀最低值"),
    MAX_TASK_HELP("max_task_help", "当前活动助力次数"),
    TASK_TARGET_VALUE("task_target_value", "任务目标值"),
;

    private String code;

    private String describe;

    ActivityRuleEnum(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }
}
