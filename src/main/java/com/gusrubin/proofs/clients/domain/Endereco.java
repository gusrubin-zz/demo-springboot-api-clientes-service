package com.gusrubin.proofs.clients.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "ENDERECO")
public class Endereco {
	
	@Id
	private Long id;
	@NotNull
	@NotEmpty
	private String estado;
	@NotNull
	@NotEmpty
	private String cidade;
	@NotNull
	@NotEmpty
	private String rua;
	private Integer numero;
	private String complemento;
	private Integer cep;
	@OneToOne
	@PrimaryKeyJoinColumn
	private Cliente cliente;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public Integer getCep() {
		return cep;
	}
	public void setCep(Integer cep) {
		this.cep = cep;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Override
	public String toString() {
		return "Endereco [id=" + id + ", estado=" + estado + ", cidade=" + cidade + ", rua=" + rua + ", numero="
				+ numero + ", complemento=" + complemento + ", cep=" + cep + ", clienteId=" + cliente.getId() + "]";
	}	

}
