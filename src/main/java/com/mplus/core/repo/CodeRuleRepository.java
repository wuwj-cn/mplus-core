package com.mplus.core.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mplus.core.entity.CodeRule;
import com.mplus.utils.DataState;

@Repository
public interface CodeRuleRepository extends BaseRepository<CodeRule, String> {

	@Query(value = "select o from CodeRule o where o.ruleId = ?")
	CodeRule findOneById(String ruleId);

	@Query(value = "select o from CodeRule o where o.ruleCode = ?1 and o.dataState = ?2")
	CodeRule findOneByCode(String ruleCode, DataState dataState);
}
