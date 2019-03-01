package com.pessoa.Template.loader;

import java.time.LocalDate;

import com.pessoa.Model.Pessoa;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PessoaTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Pessoa.class).addTemplate("valid", new Rule(){{
            add("id", random(Integer.class, range(1, 200)));
            add("nome", "felipe");
            add("ativo", "sim");
            add("data", LocalDate.now());
            add("telefone", "23454-444");
            add("cpf", "12333-33333-3333");
        }});	
	}

}
