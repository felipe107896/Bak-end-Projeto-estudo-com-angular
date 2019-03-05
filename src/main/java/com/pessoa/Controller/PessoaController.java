package com.pessoa.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pessoa.Mapper.PessoaRequestMapper;
import com.pessoa.Model.Pessoa;
import com.pessoa.Request.PessoaRequest;
import com.pessoa.ServiceImpl.PessoaServiceImpl;
import com.pessoa.service.PessoaService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;


@Api(value = "pessoas", description = "CRUD com as informações das pessoas")
@RestController
@RequestMapping("/service")
@Slf4j
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaRequestMapper pessoaMapper;
	
	@RequestMapping(value="/pessoa", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PessoaRequest> salvar(@RequestBody final PessoaRequest pessoaRequest) {
		log.info("fazendo a requisição para criação dos dados da pessoa ");
		pessoaService.savePessoa(pessoaMapper.toPessoa(pessoaRequest));
		return new ResponseEntity<>(pessoaRequest, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/pessoa", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PessoaRequest> atualizar(@RequestBody final PessoaRequest pessoaRequest) {
		log.info("fazendo a requisição para criação dos dados da pessoa ");
		 pessoaService.savePessoa(pessoaMapper.toPessoa(pessoaRequest));
		return new ResponseEntity<>(pessoaRequest, HttpStatus.CREATED);
 
	}
	
	@RequestMapping(value="/pessoa", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Pessoa>> consultar() {
		List<Pessoa> pessoas = pessoaService.findPessoas();
		return new ResponseEntity<>(pessoas, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pessoa/{codigo}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Pessoa> buscar(@PathVariable("codigo") Integer codigo) {
		final Pessoa pessoa = pessoaService.findPessoa(codigo);
		return new ResponseEntity<>(pessoa, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pessoa/{codigo}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Integer> excluir(@PathVariable("codigo") Integer codigo){
		pessoaService.deletePessoa(codigo);
		return new ResponseEntity<>(codigo, HttpStatus.CREATED);
	}
		

}
