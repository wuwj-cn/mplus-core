package com.mplus.core.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mplus.core.entity.User;

@Repository
public interface UserRepository extends BaseRepository<User, String> {

	@Query(value = "select u from User u where u.username = ?1 and u.password = ?2")
	User find(String username, String password);
	
	@Query(value = "select u from User u where u.username = ?")
	User findByUsername(String username);
}
