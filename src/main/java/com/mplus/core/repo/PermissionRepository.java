package com.mplus.core.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mplus.core.entity.Permission;
import com.mplus.enums.DataState;

@Repository
public interface PermissionRepository extends BaseRepository<Permission, String> {

	@Query(value = "from Permission where dataState = ?2 and permissionCode = ?1")
	Permission findOneByCode(String permissionCode, DataState dataState);
}
