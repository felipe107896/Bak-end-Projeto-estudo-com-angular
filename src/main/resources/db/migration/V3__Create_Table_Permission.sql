CREATE TABLE IF NOT EXISTS permission 
(
  id serial,
  nome character(80),
  description character(255),
  data date,
  telefone character(25),
  cpf character(25),
  CONSTRAINT permissionPK PRIMARY KEY (id)
);
