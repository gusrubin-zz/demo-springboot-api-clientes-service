package com.gusrubin.proofs.clients.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "CLIENTE")
public class Cliente {
	
	@Id
	@Column(name = "CLIENTE_ID", unique = true, nullable = false)
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String id;
	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	@NotEmpty
	private String cpf;
	@Column(name = "DATA_NASCIMENTO")
	private Timestamp dataDeNascimento;
	@OneToOne()
	@JoinColumn(name="ENDERECO_ID")
	private Endereco endereco;
	@OneToMany(mappedBy="cliente", cascade = CascadeType.ALL)
	private List<Telefone> telefones;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Timestamp getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(Timestamp dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefone) {
		this.telefones = telefone;
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", dataDeNascimento=" + dataDeNascimento
				+ ", enderecoId=" + endereco.getId() + ", telefoneId=" + telefones + "]";
	}	

}
