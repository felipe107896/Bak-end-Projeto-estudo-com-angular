CREATE TABLE IF NOT EXISTS users
(
  id serial,
  user_name character(255) UNIQUE,
  password character(255),
  full_name character(255),
  account_non_expired bit(1),
  account_non_locked bit(1),
  credentials_non_expired bit(1),
  enabled bit(1) DEFAULT NULL,
  CONSTRAINT permissionPK PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO postgres;

