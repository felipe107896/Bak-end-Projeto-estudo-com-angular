package com.pessoa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoa.Model.Pessoa;

public interface PessoaRepository  extends JpaRepository<Pessoa, Integer> {

}
