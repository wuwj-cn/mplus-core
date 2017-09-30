package com.mplus.core.service.impl;

import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mplus.core.entity.CodeRule;
import com.mplus.core.repo.CodeRuleRepository;
import com.mplus.core.service.CodeRuleService;
import com.mplus.enums.DataState;
import com.mplus.enums.RuleCode;
import com.mplus.utils.RuleUtil;
import com.mysql.jdbc.StringUtils;

@Service
@Transactional
public class CodeRuleServiceImpl implements CodeRuleService {

	private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();;

	@Autowired
	CodeRuleRepository codeRuleRespository;

	@Override
	public CodeRule saveCodeRule(CodeRule rule) {
		CodeRule r = codeRuleRespository.findOneByCode(rule.getRuleCode(), DataState.ENABLE);
		if (r != null) {
			throw new RuntimeException("规则编码已存在");
		}
		codeRuleRespository.save(rule);
		return rule;
	}

	@Override
	public CodeRule updateCodeRule(CodeRule rule) {
		if (StringUtils.isNullOrEmpty(rule.getRuleId())) {
			throw new RuntimeException("object id is null or empty");
		}
		rule.setUpdateAt(new Date());
		codeRuleRespository.save(rule);
		return rule;
	}

	@Override
	public void removeCodeRule(CodeRule rule) {
		rule.setDataState(DataState.DELETED);
		rule.setUpdateAt(new Date());
		codeRuleRespository.save(rule);
	}

	@Override
	public CodeRule findOneById(String ruleId) {
		return codeRuleRespository.findOneById(ruleId);
	}

	@Override
	public CodeRule findOneByCode(String ruleCode) {
		return codeRuleRespository.findOneByCode(ruleCode, DataState.ENABLE);
	}

	/**
	 * 高并发流水号生成
	 */
	public String getSerial(RuleCode ruleCode) {
		String serial = null;
		try {
			lock.readLock().lock();
			CodeRule rule = codeRuleRespository.findOneByCode(ruleCode.getName(), DataState.ENABLE);
			if(null == rule) {
				throw new RuntimeException("not set rule code");
			}
			try {
				lock.readLock().unlock();
				lock.writeLock().lock();
				serial = RuleUtil.getSerial(rule);
				rule.setCurrentValue(serial);
				codeRuleRespository.save(rule);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.readLock().lock();
				lock.writeLock().unlock();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.readLock().unlock();
		}
		return serial;
	}
}
