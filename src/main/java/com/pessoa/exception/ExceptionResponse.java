package com.pessoa.exception;

import java.util.Date;

import lombok.Data;

@Data
public class ExceptionResponse {

	private Date data;
	
	private String descErro;
	
	private String erro;
	
	
	
	public ExceptionResponse(Date data, String descErro, String erro) {
		this.data = data;
		this.descErro = descErro;
		this.erro = erro;
	}
	
	
}
