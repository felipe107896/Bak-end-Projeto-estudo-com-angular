package com.pessoa.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"codigo","nome","ativo","data","telefone","cpf"})
public class PessoaRequest {
	
	@JsonProperty("codigo")
	private Integer codigo;
 
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
