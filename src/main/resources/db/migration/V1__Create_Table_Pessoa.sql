CREATE TABLE IF NOT EXISTS tb_pessoa
(
  id_pessoa integer NOT NULL,
  nome character(80),
  ativo character(15),
  data date,
  telefone character(25),
  cpf character(25),
  CONSTRAINT tb_pessoa_pkey PRIMARY KEY (id_pessoa)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_pessoa
  OWNER TO postgres;