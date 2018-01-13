package com.gusrubin.proofs.clients.domain.endereco;

import com.gusrubin.proofs.clients.domain.endereco.Endereco;

public class EnderecoBuilder {
	
	private final Endereco target;
	
	public EnderecoBuilder() {
		this.target = new Endereco();
	}
	
	public static EnderecoBuilder create() {
		return new EnderecoBuilder();
	}
	
	public EnderecoBuilder exemploValido() {
		this.target.setEstado("SP");
		this.target.setCidade("Campinas");
		this.target.setRua("Francisco Glicério");
		this.target.setNumero(1234);
		this.target.setComplemento("Casa");
		this.target.setCep(13033000);
		return this;
	}
	
	/**
	 * Endereço inválido, faltando campo obrigatório "cidade"
	 */
	public EnderecoBuilder exemploInvalido() {
		this.target.setEstado("SP");
		this.target.setRua("Francisco Glicério");
		this.target.setNumero(1234);
		this.target.setComplemento("Casa");
		this.target.setCep(13033000);
		return this;
	}
	
	public Endereco build() {
		return this.target;
	}

}
