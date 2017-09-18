package com.mplus.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mplus.core.entity.base.BaseEntity;
import com.mplus.utils.RulePolicy;
import com.mplus.utils.RulePolicyConverter;

@Entity
@Table(name = "code_rule")
public class CodeRule extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -4498917973674072910L;
	
	@Id
    @Column(length=64)
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String ruleId;
	
	@Column(length=20, nullable = false)
	private String ruleCode;
	
	@Column(length=100, nullable = false)
	private String ruleName;
	
	@Column(length=100)
	private String rulePrefix;
	
	@Column(length=3, nullable = false)
	@Convert(converter = RulePolicyConverter.class)
	private RulePolicy rulePolicy;
	
	@Column(length=5)
	private Integer serialLength;
	
	@Column(length=13)
	private String currentValue;
	
	@Column(length=255)
	private String remark;
	
	public CodeRule() {}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleCode() {
		return ruleCode;
	}

	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRulePrefix() {
		return rulePrefix;
	}

	public void setRulePrefix(String rulePrefix) {
		this.rulePrefix = rulePrefix;
	}

	public RulePolicy getRulePolicy() {
		return rulePolicy;
	}

	public void setRulePolicy(RulePolicy rulePolicy) {
		this.rulePolicy = rulePolicy;
	}

	public Integer getSerialLength() {
		return serialLength;
	}

	public void setSerialLength(Integer serialLength) {
		this.serialLength = serialLength;
	}

	public String getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}