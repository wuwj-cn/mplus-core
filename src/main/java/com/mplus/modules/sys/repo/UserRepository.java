package com.mplus.modules.sys.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mplus.core.repo.BaseRepository;
import com.mplus.enums.DataState;
import com.mplus.modules.sys.entity.User;

@Repository
public interface UserRepository extends BaseRepository<User, String> {

	@Query(value = "select u from User u where u.username = ?1 and u.dataState = ?2")
	User findByUserName(String username, DataState dataState);
	
	@Query(value = "from User where dataState = ?2 and org.orgId = ?1")
	List<User> findByOrg(String orgId, DataState dataState);
	
	@Query(value = "from User where dataState = ?2 and userCode = ?1")
	User findOneByCode(String userCode, DataState dataState);
	
	@Query(value = "select u from User u left outer join fetch u.roles r "
			+ "where u.dataState = r.dataState and u.dataState = ?2 "
			+ "and r.roleId = ?1")
	List<User> findByRole(String roleId, DataState dataState);
}
