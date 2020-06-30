package com.pessoa.Mapper.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.pessoa.mapper.PessoaRequestMapper;
import com.pessoa.model.Pessoa;
import com.pessoa.request.PessoaRequest;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PessoaMapperTest {
	
	@InjectMocks
	private PessoaRequestMapper pessoaRequestMapper;
	
	
	@Before
	public void setup() {
		  FixtureFactoryLoader.loadTemplates("com.pessoa.Template.loader");  
	}
	
	@Test
	public void pessoaRequestMapperSucess() {
		final PessoaRequest pessoaRequest = Fixture.from(PessoaRequest.class).gimme("valido");
		final Pessoa pessoa = Fixture.from(Pessoa.class).gimme("valido");
		
		final Pessoa pessoaResult = pessoaRequestMapper.toPessoa(pessoaRequest);
		
		assertEquals(pessoa.getCodigo(), pessoaResult.getCodigo());
		assertEquals(pessoa.getAtivo(), pessoaResult.getAtivo());
		assertEquals(pessoa.getCpf(), pessoaResult.getCpf());
		assertEquals(pessoa.getData(), pessoaResult.getData());
		assertEquals(pessoa.getNome(), pessoaResult.getNome());
		assertEquals(pessoa.getTelefone(), pessoaResult.getTelefone());
		
		
	}

}
