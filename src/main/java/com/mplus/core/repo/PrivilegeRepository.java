package com.mplus.core.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mplus.core.entity.Privilege;
import com.mplus.enums.DataState;

@Repository
public interface PrivilegeRepository extends BaseRepository<Privilege, String> {

	@Query(value = "from Privilege where dataState = ?2 and priviCode = ?1")
	Privilege findOneByCode(String priviCode, DataState dataState);
}
