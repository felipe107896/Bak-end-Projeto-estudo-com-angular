package com.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoa.model.Pessoa;
import com.pessoa.model.User;

@Repository 
public interface UserRepository  extends JpaRepository<User, Integer> {

}
