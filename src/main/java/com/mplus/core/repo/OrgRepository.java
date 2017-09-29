/**
 * 
 */
package com.mplus.core.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.mplus.core.entity.Org;
import com.mplus.utils.DataState;

/**
 * @author wuwj
 *
 */
public interface OrgRepository extends BaseRepository<Org, String> {

	@Query(value = "from Org where dataState = ?2 and orgCode = ?1")
	Org findOneByCode(String orgCode, DataState dataState);
	
	@Query(value = "from Org where dataState = ?2 and parentOrgId = ?1")
	List<Org> findOrgsByParent(String parentOrgId, DataState dataState);
}
