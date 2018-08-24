package com.mplus.modules.sys.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mplus.core.base.repo.BaseRepository;
import com.mplus.modules.sys.entity.User;

@Repository
public interface UserRepository extends BaseRepository<User, String> {

	@Query(value = "select u from User u where u.username = ?1 and u.status = ?2")
	User findByUserName(String username, String status);
	
	@Query(value = "from User where status = ?2 and org.id = ?1")
	List<User> findByOrg(String orgId, String status);
	
	@Query(value = "from User where status = ?2 and userCode = ?1")
	User findOneByCode(String userCode, String status);
	
	@Query(value = "select u from User u left outer join fetch u.roles r "
			+ "where u.status = r.status and u.status = ?2 "
			+ "and r.id = ?1")
	List<User> findByRole(String roleId, String status);
}
