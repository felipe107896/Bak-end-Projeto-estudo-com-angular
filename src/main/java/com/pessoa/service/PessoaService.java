package com.pessoa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pessoa.model.Pessoa;


public interface PessoaService {
	
	public void savePessoa(final Pessoa pessoa);
	
	public void deletePessoa(final Integer codigo);
	
	public void updatePessoa(final Pessoa pessoa);
	
	public Pessoa findPessoa(final Integer codigo);
	
	public List<Pessoa> findPessoas();
	
	

}
