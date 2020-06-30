package com.pessoa.model;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Getter;

@Table(name="tb_pessoa")
@Entity
@Data
public class Pessoa implements Serializable {
	
	private static long serialVersionUID = -308849398499L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id_pessoa")
	private Integer codigo;
 
	@Column(name="nome")
	private String  nome;
 
	@Column(name="ativo")
	private String ativo;
	
	@Column(name="data")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDate data;
	
	@Column(name="telefone")
	private String telefone;
	
	@Column(name="cpf")
	private String cpf;

}
