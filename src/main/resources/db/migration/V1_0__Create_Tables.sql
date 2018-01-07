CREATE SEQUENCE SEQ_CLIENTE START 1;
CREATE SEQUENCE SEQ_ENDERECO START 1;
CREATE SEQUENCE SEQ_TELEFONE START 1;

create table ENDERECO (
	endereco_id bigint PRIMARY KEY,
	estado text not null,
	cidade text not null,
	rua text not null,
	numero integer null,
	complemento text null,
	cep bigint null
);

create table CLIENTE (
	cliente_id text PRIMARY KEY,
	nome text not null,
	cpf text not null,
	data_nascimento timestamp,
	endereco_id bigint references ENDERECO ON DELETE CASCADE
);

CREATE TABLE TELEFONE (
    telefone_id bigint PRIMARY KEY,
    tipo varchar(7) NOT NULL,
    numero text NOT NULL,
    cliente_id text references CLIENTE (cliente_id)
);