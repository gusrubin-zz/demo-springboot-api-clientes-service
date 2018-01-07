package com.gusrubin.proofs.clients.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "TELEFONE")
public class Telefone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	@NotEmpty
	private String tipo;
	@NotNull
	private String numero;
	@ManyToOne
	private Cliente cliente;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public String toString() {
		return "Telefone [id=" + id + ", tipo=" + tipo + ", numero=" + numero + ", clienteId=" + cliente.getId() + "]";
	}	

}
