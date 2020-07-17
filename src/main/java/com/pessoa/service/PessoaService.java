package com.pessoa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pessoa.model.Pessoa;
import com.pessoa.vo.PessoaVO;


public interface PessoaService {
	
	public void savePessoa(final Pessoa pessoa);
	
	public void deletePessoa(final Integer codigo);
	
	public void updatePessoa(final Pessoa pessoa);
	
	public Pessoa findPessoa(final Integer codigo);
	

	public Pessoa disablePessoa(final Integer codigo);
	
	public Page<PessoaVO> findPessoas(Pageable pageable);
	
	
	
	

}
