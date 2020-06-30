package com.pessoa.request;

import lombok.Data;

@Data
public class PessoaRequest {
	
	private Integer codigo;
 
	private String  nome;
 
	private String ativo;
	
	private String data;
	
	private String telefone;
	
	private String cpf;

}
