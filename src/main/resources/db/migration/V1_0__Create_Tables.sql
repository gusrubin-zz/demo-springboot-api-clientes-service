create table CLIENTE (
	id text PRIMARY key,
	nome text not null,
	cpf varchar(11) not null,
	data_nascimento timestamp	
);

create table ENDERECO (
	id bigint,
	estado text not null,
	cidade text not null,
	rua text not null,
	numero integer,
	complemento text,
	cep bigint,
	cliente_id text references cliente
);

CREATE TABLE TELEFONE (
    id bigint PRIMARY KEY,
    tipo varchar(7) NOT NULL,
    numero bigint NOT null    
);

CREATE TABLE CLIENTE_TELEFONE (
  	client_id text,
  	telefone_id bigint
);