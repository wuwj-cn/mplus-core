package com.mplus.core.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mplus.core.entity.Menu;
import com.mplus.core.entity.Org;
import com.mplus.enums.DataState;

/**
 * @author wuwj
 *
 */
@Repository
public interface MenuRepository extends BaseRepository<Menu, String> {

	@Query(value = "from Menu where dataState = ?2 and menuCode = ?1")
	Org findOneByCode(String menuCode, DataState dataState);
	
	@Query(value = "from Menu where dataState = ?2 and parentMenuId = ?1")
	List<Org> findMenusByParent(String parentMenuId, DataState dataState);
	
}
