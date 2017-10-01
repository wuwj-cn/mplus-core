package com.mplus.core.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mplus.core.entity.Role;
import com.mplus.enums.DataState;

@Repository
public interface RoleRepository extends BaseRepository<Role, String> {

	@Query(value = "from Role where dataState = ?2 and roleCode = ?1")
	Role findOneByCode(String roleCode, DataState dataState);
	
}
