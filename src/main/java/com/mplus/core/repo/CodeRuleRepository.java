package com.mplus.core.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mplus.core.entity.CodeRule;

@Repository
public interface CodeRuleRepository extends CrudRepository<CodeRule, String>, JpaSpecificationExecutor<CodeRule> {

	@Query(value = "select o from CodeRule o where o.ruleId = ?")
	CodeRule findOneById(String ruleId);

	@Query(value = "select o from CodeRule o where o.ruleCode = ?")
	CodeRule findOneByCode(String ruleCode);
}
