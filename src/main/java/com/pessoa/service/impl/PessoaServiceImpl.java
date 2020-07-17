package com.pessoa.service.impl;

import java.util.List;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.dozermapper.core.DozerConverter;
import com.pessoa.exception.ResourceNotFoundException;
import com.pessoa.model.Pessoa;
import com.pessoa.repository.PessoaRepository;
import com.pessoa.service.PessoaService;
import com.pessoa.vo.PessoaVO;

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
	public Page<PessoaVO> findPessoas(Pageable pageable) {
		Page<Pessoa> pessoas = Optional.ofNullable(pessoaRepository.findAll(pageable)).orElseThrow(() -> new ResourceNotFoundException("Pessoas não encontrado "));
		
		return pessoas.map(this::convertPersonVo);
		
	}
	
	public PessoaVO convertPersonVo(Pessoa pessoa) {
		return com.pessoa.converter.vo.DozerConverter.parseObject(pessoa, PessoaVO.class);
		
	}
	
	@Override
	public void deletePessoa(final Integer codigo) {
		final Pessoa pessoaValue = findPessoa(codigo);
		Optional.ofNullable(pessoaValue).ifPresent(pessoaResult -> pessoaRepository.delete(pessoaResult));
	}
	
	@org.springframework.transaction.annotation.Transactional
	@Override
	public Pessoa disablePessoa(Integer codigo) {
		// TODO Auto-generated method stub
		pessoaRepository.disablePerson(codigo);
		final Pessoa pessoa = pessoaRepository.findById(codigo).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrado "));

		return pessoa;
	}
	

}
