package com.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pessoa.model.Pessoa;

@Repository 
public interface PessoaRepository  extends JpaRepository<Pessoa, Integer> {
	
	@Modifying
	@Query("UPDATE Pessoa p SET  p.ativo = 'não' WHERE p.id =:id")
	void disablePerson(@Param("id") Integer id);

}
