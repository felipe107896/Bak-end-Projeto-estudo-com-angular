package com.pessoa.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.ResponseEntity.ok;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoa.converter.vo.DozerConverter;
import com.pessoa.model.Permission;
import com.pessoa.model.User;
import com.pessoa.repository.UserRepository;
import com.pessoa.request.PessoaRequest;
import com.pessoa.security.AccountCredentialsVO;
import com.pessoa.security.jwt.JwtTokenProvider;
import com.pessoa.service.UserService;
import com.pessoa.service.UsersService;
import com.pessoa.vo.PessoaVO;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UsersService userSerive;
	
	
	
	@ApiOperation(value ="Authentication a user by credentials")
	@PostMapping(value="v1" ,consumes = {"application/json","application/xml","application/x-yaml" }, produces = {"application/json","application/xml","application/x-yaml" })
	public ResponseEntity salvar(@RequestBody final AccountCredentialsVO accountCredentialsVO) {
	
		try {
			//authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountCredentialsVO.getUserName(),accountCredentialsVO.getPassword()));
			
			User user = userSerive.findByUsernam(accountCredentialsVO.getUserName());
			
			String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
			
			Map<Object, Object> model = new HashMap<>();
			model.put("username", user.getUsername());
			model.put("token", token);
			return ok(model);
			
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid username or password");
		}
	}
	

}
