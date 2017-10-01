package com.mplus.core.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mplus.core.entity.User;
import com.mplus.enums.DataState;

@Repository
public interface UserRepository extends BaseRepository<User, String> {

	@Query(value = "select u from User u where u.userName = ?1 and u.password = ?2")
	User find(String username, String password);
	
	@Query(value = "select u from User u where u.userName = ?1")
	User findByUserName(String userName);
	
	@Query(value = "from User where dataState = ?2 and org.orgId = ?1")
	List<User> findByOrg(String orgId, DataState dataState);
}
