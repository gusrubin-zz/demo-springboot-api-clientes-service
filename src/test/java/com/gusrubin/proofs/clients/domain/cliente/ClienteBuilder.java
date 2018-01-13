package com.gusrubin.proofs.clients.domain.cliente;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import com.gusrubin.proofs.clients.domain.cliente.Cliente;
import com.gusrubin.proofs.clients.domain.endereco.EnderecoBuilder;

public class ClienteBuilder {
	
private final Cliente target;
	
	public ClienteBuilder() {
		this.target = new Cliente();
	}
	
	public static ClienteBuilder create() {
		return new ClienteBuilder();
	}
	
	public ClienteBuilder exemploValido() {
		this.target.setNome("Gustavo");
		this.target.setCpf("12345678901");
		this.target.setDataDeNascimento(Timestamp.from(Instant.parse("1983-07-07T12:00:00.00Z")));
		this.target.setEndereco(EnderecoBuilder.create().exemploValido().build());
		return this;
	}
	
	public ClienteBuilder exemploValido2() {
		this.target.setNome("Gustavo");
		this.target.setCpf("12345678902");
		this.target.setDataDeNascimento(Timestamp.from(Instant.parse("1983-07-07T12:00:00.00Z")));
		this.target.setEndereco(EnderecoBuilder.create().exemploValido().build());
		return this;
	}
	
	/**
	 * Cliente inválido, faltando campo obrigatório "cpf"
	 */
	public ClienteBuilder exemploInvalido() {
		this.target.setNome("Gustavo");
		this.target.setDataDeNascimento(Timestamp.from(Instant.parse("1983-07-07T12:00:00.00Z")));
		this.target.setEndereco(EnderecoBuilder.create().exemploValido().build());
		return this;
	}
	
	public Cliente build() {
		return this.target;
	}

}
