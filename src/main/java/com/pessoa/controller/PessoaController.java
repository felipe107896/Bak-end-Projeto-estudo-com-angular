package com.pessoa.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pessoa.converter.vo.DozerConverter;
import com.pessoa.mapper.PessoaRequestMapper;
import com.pessoa.model.Pessoa;
import com.pessoa.request.PessoaRequest;
import com.pessoa.service.PessoaService;
import com.pessoa.service.impl.PessoaServiceImpl;
import com.pessoa.vo.PessoaVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@Api(value = "pessoas", description = "CRUD com as informações das pessoas", tags = {"Pessoas endpoint"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/pessoa")
@Slf4j
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaRequestMapper pessoaMapper;
	
	@Autowired
	private PagedResourcesAssembler<PessoaVO> assembler;
	
	@ApiOperation(value ="Salvar as pessoas")
	@PostMapping(value="v1",consumes = {"application/json","application/xml","application/x-yaml" }, produces = {"application/json","application/xml","application/x-yaml" })
	public ResponseEntity<PessoaVO> salvar(@RequestBody final PessoaRequest pessoaRequest) {
		log.info("fazendo a requisição para criação dos dados da pessoa ");
		pessoaService.savePessoa(pessoaMapper.toPessoa(pessoaRequest));
		 PessoaVO pessoaVO = DozerConverter.parseObject(pessoaRequest, PessoaVO.class);
			pessoaVO.add(linkTo(methodOn(PessoaController.class).buscar(pessoaVO.getKey())).withSelfRel());
		return new ResponseEntity<>(pessoaVO, HttpStatus.CREATED);
	}
	
	@ApiOperation(value ="Alterar dados das pessoas")
	@PutMapping(value="v1",consumes = {"application/json","application/xml","application/x-yaml" }, produces = {"application/json","application/xml","application/x-yaml" })
	public ResponseEntity<PessoaVO> atualizar(@RequestBody final PessoaRequest pessoaRequest) {
		log.info("fazendo a requisição para criação dos dados da pessoa ");
		 pessoaService.savePessoa(pessoaMapper.toPessoa(pessoaRequest));
		 PessoaVO pessoaVO = DozerConverter.parseObject(pessoaRequest, PessoaVO.class);
			pessoaVO.add(linkTo(methodOn(PessoaController.class).buscar(pessoaVO.getKey())).withSelfRel());
		return new ResponseEntity<>(pessoaVO, HttpStatus.CREATED);
 
	}
	
	@ApiOperation(value ="Buscar todas as pessoas")
	@GetMapping(value="/v1",produces = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<?> consultar(@RequestParam(value="page",defaultValue= "0") int page,
			@RequestParam(value="limit",defaultValue= "12") int limit,
			@RequestParam(value="direction",defaultValue= "asc") String  direction) {
		
		Direction sort;
		if(direction.equalsIgnoreCase("asc")) {
			 sort= Direction.ASC;
		} else {
			 sort= Direction.DESC;
		}
		Pageable pageable = PageRequest.of(page,limit,Sort.by(sort, "nome"));
		Page<PessoaVO> pessoas = pessoaService.findPessoas(pageable);
		
		pessoas.stream().forEach(value -> value.add(linkTo(methodOn(PessoaController.class).buscar(value.getKey())).withSelfRel()));
		
		PagedResources<?> resources = assembler.toResource(pessoas);
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	@ApiOperation(value ="Buscar todas as pessoas por código")
	@GetMapping(value="/v1/{codigo}",produces = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<PessoaVO> buscar(@PathVariable("codigo") Integer codigo) {
		final Pessoa pessoa = pessoaService.findPessoa(codigo);
		PessoaVO pessoaVO = DozerConverter.parseObject(pessoa, PessoaVO.class);
		pessoaVO.add(linkTo(methodOn(PessoaController.class).buscar(codigo)).withSelfRel());
		return new ResponseEntity<>(pessoaVO, HttpStatus.OK);
	}
	
	@ApiOperation(value ="Desabilitar pessoa")
	@PatchMapping(value="/v1/{codigo}",produces = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<PessoaVO> desabilitarPessoa(@PathVariable("codigo") Integer codigo) {
		final Pessoa pessoa = pessoaService.disablePessoa(codigo);
		PessoaVO pessoaVO = DozerConverter.parseObject(pessoa, PessoaVO.class);
		pessoaVO.add(linkTo(methodOn(PessoaController.class).buscar(codigo)).withSelfRel());
		return new ResponseEntity<>(pessoaVO, HttpStatus.OK);
	}
	
	
	@ApiOperation(value ="Excluir pessoa")
	@DeleteMapping(value="/v1/{codigo}")
	public ResponseEntity<Integer> excluir(@PathVariable("codigo") Integer codigo){
		pessoaService.deletePessoa(codigo);
		return new ResponseEntity<>(codigo, HttpStatus.CREATED);
	}
		

}
