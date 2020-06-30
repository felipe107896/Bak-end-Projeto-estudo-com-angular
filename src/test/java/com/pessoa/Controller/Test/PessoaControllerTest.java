package com.pessoa.Controller.Test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.pessoa.model.Pessoa;
import com.pessoa.request.PessoaRequest;
import com.pessoa.service.PessoaService;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mvc;
	
	@MockBean
	private PessoaService pessoaService;
	
	private static final String URI_INVALIDA = "/service/pessoa";
	
	private static final String PESSOA_SERVICE = "/service/pessoa/";
	
	private static final Integer CODIGO = 333;
	private static final String NOME = "felipe";
	private static final String ATIVO = "sim";
	private static final String TELEFONE = "23454-444";
	private static final String CPF = "12333-33333-3333";
	

	@Before
	public void setup() {
	     this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	     FixtureFactoryLoader.loadTemplates("com.pessoa.Template.loader");
	     Pessoa pessoa = Fixture.from(Pessoa.class).gimme("valido");
	     BDDMockito.given(this.pessoaService.findPessoa(CODIGO)).willReturn(pessoa);
	    
	}


	@Test
	public void sucessPostTest() throws Exception {
		PessoaRequest pessoa = Fixture.from(PessoaRequest.class).gimme("valido");
		
	    Gson gson = new Gson();
	      String json = gson.toJson(pessoa);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
	            .post("/service/pessoa")
	            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
	            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
	            .content(json);
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();

	    MockHttpServletResponse response = result.getResponse();

	    assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
	public void sucessPutTest() throws Exception {
		PessoaRequest pessoa = Fixture.from(PessoaRequest.class).gimme("valido");
		
	    Gson gson = new Gson();
	      String json = gson.toJson(pessoa);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
	            .put("/service/pessoa")
	            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
	            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
	            .content(json);
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();

	    MockHttpServletResponse response = result.getResponse();

	    assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
    public void failedSeachForPessoa() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.get(URI_INVALIDA + CODIGO)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());   
    }
	
	@Test
	public void findPessoaSucess() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get(PESSOA_SERVICE + CODIGO)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect( status().isOk())
				.andExpect(jsonPath("$.codigo").value(CODIGO))
				.andExpect(jsonPath("$.nome").value(NOME))
				.andExpect(jsonPath("$.cpf").value(CPF))
				.andExpect(jsonPath("$.telefone").value(TELEFONE))
				.andExpect(jsonPath("$.ativo").value(ATIVO));
	}
	
	@Test
    public void findPessoas() throws Exception {
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(PESSOA_SERVICE)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();   
		
		String content = result.getResponse().getContentAsString();
		
		Assert.assertNotNull(content);
    }
	
	@Test
	public void deletePessoaSucess() throws Exception {

		mvc.perform(MockMvcRequestBuilders.delete(PESSOA_SERVICE + CODIGO)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isCreated());
	}
	
	

}

