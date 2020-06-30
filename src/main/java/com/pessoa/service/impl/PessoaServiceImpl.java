package com.pessoa.service.impl;

import java.util.List;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoa.exception.ResourceNotFoundException;
import com.pessoa.model.Pessoa;
import com.pessoa.repository.PessoaRepository;
import com.pessoa.service.PessoaService;

@Service
@Transactional
public class PessoaServiceImpl implements PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public void savePessoa(final Pessoa pessoa) {
		Optional.ofNullable(pessoa).ifPresent(pessoaValue -> pessoaRepository.save(pessoaValue));
		
	}
	@Override
	public void updatePessoa(final Pessoa pessoa) {
		final Pessoa pessoaValue = findPessoa(pessoa.getCodigo());
		Optional.ofNullable(pessoaValue).ifPresent(pessoaResult -> pessoaRepository.save(pessoaResult));	
	}

	@Override
	public Pessoa findPessoa(final Integer codigo) {
			final Pessoa pessoa = pessoaRepository.findById(codigo).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrado "));
	
			return pessoa;
}

	@Override
	public List<Pessoa> findPessoas() {
		return Optional.ofNullable(pessoaRepository.findAll()).orElse(null);
		
	}
	@Override
	public void deletePessoa(final Integer codigo) {
		final Pessoa pessoaValue = findPessoa(codigo);
		Optional.ofNullable(pessoaValue).ifPresent(pessoaResult -> pessoaRepository.delete(pessoaResult));
	}
	

}
