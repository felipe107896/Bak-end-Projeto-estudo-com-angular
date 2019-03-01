package com.pessoa.Controller.Test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.common.net.MediaType;
import com.pessoa.Controller.PessoaController;
import com.pessoa.Model.Pessoa;
import com.pessoa.ServiceImpl.PessoaServiceImpl;
import com.pessoa.service.PessoaService;

import br.com.six2six.fixturefactory.Fixture;

@RunWith(SpringRunner.class)
@WebMvcTest(PessoaController.class)
public class PessoaControllerTest {
	
	@Autowired 
	private MockMvc mvc;
	
	@Mock
	private PessoaService pessoaService;


	@Test
	public void sucessPostTest() {
		Pessoa pessoa = Fixture.from(Pessoa.class).gimme("valid");
		
	    Gson gson = new Gson();
	      String json = gson.toJson(pessoa);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
	            .post("/students/Student1/courses")
	            .accept(org.springframework.http.MediaType.APPLICATION_JSON)
	            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
	            .content(json);
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();

	    MockHttpServletResponse response = result.getResponse();

	    assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

}

