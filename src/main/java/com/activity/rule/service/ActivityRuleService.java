package com.activity.rule.service;


import com.activity.rule.pojo.vo.ActRuleDetailVO;

import java.util.List;


public interface ActivityRuleService {
    /**
     * 根据活动key查询活动中已选择的风控规则和规则配置的对应值
     * @param actKey
     * @param ruleCode
     * @return
     */
    List<ActRuleDetailVO> findRuleDetail(String actKey, String ruleCode);

}
