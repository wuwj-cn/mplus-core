package com.mplus.modules.sys.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mplus.core.base.repo.BaseRepository;
import com.mplus.modules.sys.entity.Role;

@Repository
public interface RoleRepository extends BaseRepository<Role, String> {

	@Query(value = "from Role where status = ?2 and roleCode = ?1")
	Role findOneByCode(String roleCode, String status);
	
}
