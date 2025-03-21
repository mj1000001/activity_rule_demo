package com.activity.rule.mapper;

import com.activity.rule.pojo.po.BizActivityRulePo;
import com.activity.rule.pojo.vo.ActRuleDetailVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface BizActivityRuleDao extends BaseMapper<BizActivityRulePo> {
    /**
     * 查询活动规则
     * @param actKey
     * @return
     */
    List<ActRuleDetailVO> findRuleDetailVOS(@Param("actKey") String actKey, @Param("ruleCode") String ruleCode);
}
