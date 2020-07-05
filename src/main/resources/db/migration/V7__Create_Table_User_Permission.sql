CREATE TABLE IF NOT EXISTS user_permission (
  id_user int NOT NULL,
  id_permission int NOT NULL,
  PRIMARY KEY (id_user,id_permission),
  CONSTRAINT fk_user_permission FOREIGN KEY (id_user) REFERENCES users (id),
  CONSTRAINT fk_user_permission_permission FOREIGN KEY (id_permission) REFERENCES permission (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_permission 
  OWNER TO postgres;