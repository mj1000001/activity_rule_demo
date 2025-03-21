package com.activity.rule.service.impl;

import com.activity.rule.mapper.BizActivityRuleDao;
import com.activity.rule.pojo.po.BizActivityRulePo;
import com.activity.rule.service.ActivityRuleService;
import com.activity.rule.pojo.vo.ActRuleDetailVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class ActivityRuleServiceImpl extends ServiceImpl<BizActivityRuleDao, BizActivityRulePo> implements ActivityRuleService {

    @Autowired
    private BizActivityRuleDao bizActivityRuleDao;


    @Override
    public List<ActRuleDetailVO> findRuleDetail(String actKey, String ruleCode) {
        return bizActivityRuleDao.findRuleDetailVOS(actKey, ruleCode);
    }


}
