package com.mplus.core.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mplus.core.entity.CodeRule;

@Repository
public interface CodeRuleRepository extends BaseRepository<CodeRule, String>, JpaSpecificationExecutor<CodeRule> {

	@Query(value = "select o from CodeRule o where o.ruleId = ? and o.dataState = :#{0}")
	CodeRule findOneById(String ruleId);

	@Query(value = "select o from CodeRule o where o.ruleCode = ? and o.dataState = :#{DataState.ENABLE}")
	CodeRule findOneByCode(String ruleCode);
}
