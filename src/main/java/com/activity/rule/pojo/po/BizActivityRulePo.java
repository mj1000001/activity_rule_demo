package com.activity.rule.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 活动规则表
 * </p>
 *
 * @author lxWu
 * @since 2023-07-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("biz_activity_rule")
@ApiModel(value="BizActivityRulePo对象", description="活动规则表")
public class BizActivityRulePo extends Model<BizActivityRulePo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "规则主键ID")
    @TableId(value = "activity_rule_key", type = IdType.NONE)
    private String activityRuleKey;

    @ApiModelProperty(value = "活动类型：1：拼团，2：砍一刀")
    @TableField("act_type")
    private Integer actType;

    @ApiModelProperty(value = "规则的名称")
    @TableField("rule_code")
    private String ruleCode;

    @ApiModelProperty(value = "规则的值")
    @TableField("rule_value")
    private Float ruleValue;

    @ApiModelProperty(value = "活动key")
    @TableField("act_key")
    private String actKey;

    @ApiModelProperty(value = "规则主键ID")
    @TableField("rule_key")
    private String ruleKey;

    @ApiModelProperty(value = "删除状态 0未删，1已删")
    @TableField("is_delete")
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private String createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "create_user_key",fill = FieldFill.INSERT)
    private String createUserKey;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private String updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "update_user_key",fill = FieldFill.INSERT_UPDATE)
    private String updateUserKey;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;


    @Override
    protected Serializable pkVal() {
        return this.activityRuleKey;
    }

}
