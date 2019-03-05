package com.pessoa.Mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.pessoa.Model.Pessoa;
import com.pessoa.Request.PessoaRequest;

@Component
public class PessoaRequestMapper {
	
	public Pessoa toPessoa(PessoaRequest pessoaRequest) {
		final Pessoa pessoa = new Pessoa();
		pessoa.setCodigo(pessoaRequest.getCodigo());
		pessoa.setAtivo(pessoaRequest.getAtivo());
		pessoa.setCpf(pessoaRequest.getCpf());
		pessoa.setData(LocalDate.parse(pessoaRequest.getData()));
		pessoa.setNome(pessoaRequest.getNome());
		pessoa.setTelefone(pessoaRequest.getTelefone());
		return pessoa;
	}

}
