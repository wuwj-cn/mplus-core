package com.mplus.core.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	@Query("select u from User u where u.username = :username and u.password = :password")
	User find(String username, String password);
	
	@Query("select u from User u where u.username = :username")
	User findByUsername(String username);
}
