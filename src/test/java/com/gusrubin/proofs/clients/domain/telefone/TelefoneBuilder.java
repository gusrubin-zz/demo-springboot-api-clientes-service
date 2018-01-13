package com.gusrubin.proofs.clients.domain.telefone;

import com.gusrubin.proofs.clients.domain.telefone.Telefone;
import com.gusrubin.proofs.clients.domain.telefone.TelefoneType;

public class TelefoneBuilder {
	
	private final Telefone target;
	
	public TelefoneBuilder() {
		this.target = new Telefone();
	}
	
	public static TelefoneBuilder create() {
		return new TelefoneBuilder();
	}
	
	public TelefoneBuilder fixo() {
		this.target.setTipo(TelefoneType.FIXO.name());
		this.target.setNumero("1932321222");
		return this;
	}
	
	public TelefoneBuilder celular() {
		this.target.setTipo(TelefoneType.CELULAR.name());
		this.target.setNumero("19999111222");
		return this;
	}
	
	public TelefoneBuilder tipoInvalido() {
		this.target.setTipo("errado");
		this.target.setNumero("19999111222");
		return this;
	}
	
	public TelefoneBuilder numeroInvalido() {
		this.target.setTipo(TelefoneType.FIXO.name());
		this.target.setNumero("19999111");
		return this;
	}
	
	public Telefone build() {
		return this.target;
	}

}
