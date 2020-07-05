package com.pessoa.vo;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

import lombok.Data;

@Data
public class PessoaVO extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	@Mapping("codigo")
	@JsonProperty("id")
	private Integer key;
	
	@JsonProperty("nome")
	private String  nome;
	
	@JsonProperty("ativo")
	private String ativo;
	
	@JsonProperty("data")
	private String data;
	
	@JsonProperty("telefone")
	private String telefone;
	
	@JsonProperty("cpf")
	private String cpf;

}
