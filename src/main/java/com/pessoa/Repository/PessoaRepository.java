package com.pessoa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoa.Model.Pessoa;

@Repository 
public interface PessoaRepository  extends JpaRepository<Pessoa, Integer> {

}
