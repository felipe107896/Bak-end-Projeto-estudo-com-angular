package com.pessoa.Template.loader;

import java.time.LocalDate;

import com.pessoa.model.Pessoa;
import com.pessoa.request.PessoaRequest;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PessoaRequestTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(PessoaRequest.class).addTemplate("valido", new Rule(){{
            add("codigo", 333);
            add("nome", "felipe");
            add("ativo", "sim");
            add("data", "2018-10-10");
            add("telefone", "23454-444");
            add("cpf", "12333-33333-3333");
        }});	
	}

}
