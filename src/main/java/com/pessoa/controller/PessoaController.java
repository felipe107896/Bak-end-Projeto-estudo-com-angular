package com.pessoa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pessoa.converter.vo.DozerConverter;
import com.pessoa.mapper.PessoaRequestMapper;
import com.pessoa.model.Pessoa;
import com.pessoa.request.PessoaRequest;
import com.pessoa.service.PessoaService;
import com.pessoa.service.impl.PessoaServiceImpl;
import com.pessoa.vo.PessoaVO;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;


@Api(value = "pessoas", description = "CRUD com as informações das pessoas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/pessoa")
@Slf4j
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaRequestMapper pessoaMapper;
	
	@PostMapping(value="v1",consumes = {"application/json","application/xml","application/x-yaml" }, produces = {"application/json","application/xml","application/x-yaml" })
	public ResponseEntity<PessoaRequest> salvar(@RequestBody final PessoaRequest pessoaRequest) {
		log.info("fazendo a requisição para criação dos dados da pessoa ");
		pessoaService.savePessoa(pessoaMapper.toPessoa(pessoaRequest));
		return new ResponseEntity<>(pessoaRequest, HttpStatus.CREATED);
	}
	
	@PutMapping(value="v1",consumes = {"application/json","application/xml","application/x-yaml" }, produces = {"application/json","application/xml","application/x-yaml" })
	public ResponseEntity<PessoaRequest> atualizar(@RequestBody final PessoaRequest pessoaRequest) {
		log.info("fazendo a requisição para criação dos dados da pessoa ");
		 pessoaService.savePessoa(pessoaMapper.toPessoa(pessoaRequest));
		return new ResponseEntity<>(pessoaRequest, HttpStatus.CREATED);
 
	}
	
	@GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<List<PessoaVO>> consultar() {
		List<Pessoa> pessoas = pessoaService.findPessoas();
		return new ResponseEntity<>(DozerConverter.parseListObjects(pessoas, PessoaVO.class), HttpStatus.OK);
	}
	
	@GetMapping(value="/v1/{codigo}",produces = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<PessoaVO> buscar(@PathVariable("codigo") Integer codigo) {
		final Pessoa pessoa = pessoaService.findPessoa(codigo);
		return new ResponseEntity<>(DozerConverter.parseObject(pessoa, PessoaVO.class), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/v1/{codigo}")
	public ResponseEntity<Integer> excluir(@PathVariable("codigo") Integer codigo){
		pessoaService.deletePessoa(codigo);
		return new ResponseEntity<>(codigo, HttpStatus.CREATED);
	}
		

}
