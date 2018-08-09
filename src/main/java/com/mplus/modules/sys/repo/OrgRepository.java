/**
 * 
 */
package com.mplus.modules.sys.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mplus.core.repo.BaseRepository;
import com.mplus.enums.DataState;
import com.mplus.modules.sys.entity.Org;

/**
 * @author wuwj
 *
 */
@Repository
public interface OrgRepository extends BaseRepository<Org, String> {

	@Query(value = "from Org where dataState = ?2 and orgCode = ?1")
	Org findOneByCode(String orgCode, DataState dataState);
	
	@Query(value = "from Org where dataState = ?2 and parentOrgId = ?1")
	List<Org> findOrgsByParent(String parentOrgId, DataState dataState);
}
