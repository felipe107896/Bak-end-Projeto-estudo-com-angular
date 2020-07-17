package com.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pessoa.model.Pessoa;
import com.pessoa.model.User;

@Repository 
public interface UserRepository  extends JpaRepository<User, Integer> {
	
	
	@Query("SELECT u from User u WHERE u.username = :userName")
	User findByUsername(@Param("userName") String username);
	
	

}
