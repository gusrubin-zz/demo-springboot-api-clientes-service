package com.gusrubin.proofs.clients.interfaces;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.gusrubin.proofs.clients.domain.Cliente;
import com.gusrubin.proofs.clients.domain.Endereco;
import com.gusrubin.proofs.clients.domain.Telefone;

public class ClientePayloadConverter {
	
	private ClientePayloadConverter() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static Cliente toDomain(ClientePayload clientePayload) {
		Cliente cliente = new Cliente();		
		if (clientePayload != null) {
			if (clientePayload.getNome() != null) {
				cliente.setNome(clientePayload.getNome());
			}
			if (clientePayload.getCpf() != null) {
				cliente.setCpf(String.valueOf(clientePayload.getCpf()));
			}
			if (clientePayload.getDataDeNascimento() != null) {
				cliente.setDataDeNascimento(Timestamp.valueOf(clientePayload.getDataDeNascimento()));
			}
		}
		
		Endereco endereco = new Endereco();
		if (clientePayload.getEndereco() != null) {			
			if (clientePayload.getEndereco().getEstado() != null) {
				endereco.setEstado(clientePayload.getEndereco().getEstado());
			}
			if (clientePayload.getEndereco().getCidade() != null) {
				endereco.setCidade(clientePayload.getEndereco().getCidade());
			}
			if (clientePayload.getEndereco().getRua() != null) {
				endereco.setRua(clientePayload.getEndereco().getRua());
			}
		}
		cliente.setEndereco(endereco);
		
		List<Telefone> telefones = new ArrayList<>();
		if (clientePayload.getTelefones() != null) {
			clientePayload.getTelefones().forEach(c -> {
				Telefone telefone = new Telefone();
				if (c.getTipo() != null) {
					telefone.setTipo(c.getTipo());
				}
				if (c.getNumero() != null) {
					telefone.setNumero(c.getNumero());
				}
				telefones.add(telefone);
			});			
		}		
		cliente.setTelefones(telefones);
		
		return cliente;
	}
	
	public static ClientePayload toPayload(Cliente cliente) {
		ClientePayload clientePayload = new ClientePayload();
		if (cliente.getNome() != null) {
			clientePayload.setNome(cliente.getNome());
		}
		if (cliente.getCpf() != null) {
			clientePayload.setCpf(cliente.getCpf());
		}
		if (cliente.getDataDeNascimento() != null) {
			clientePayload.setDataDeNascimento(cliente.getDataDeNascimento().toString());
		}
		
		ClientePayload.Endereco endereco = new ClientePayload.Endereco();
		if (cliente.getEndereco() != null) {
			if (cliente.getEndereco().getEstado() != null) {
				endereco.setEstado(cliente.getEndereco().getEstado());
			}
			if (cliente.getEndereco().getCidade() != null) {
				endereco.setCidade(cliente.getEndereco().getCidade());
			}
			if (cliente.getEndereco().getRua() != null) {
				endereco.setRua(cliente.getEndereco().getRua());
			}
			if (cliente.getEndereco().getNumero() != null) {
				endereco.setNumero(cliente.getEndereco().getNumero());
			}
			if (cliente.getEndereco().getComplemento() != null) {
				endereco.setComplemento(cliente.getEndereco().getComplemento() );
			}
			if (cliente.getEndereco().getCep() != null) {
				endereco.setCep(cliente.getEndereco().getCep());
			}
		}
		clientePayload.setEndereco(endereco);
		
		List<ClientePayload.Telefone> telefones = new ArrayList<>();
		if (cliente.getTelefones() != null) {
			cliente.getTelefones().forEach(c -> {
				ClientePayload.Telefone telefone = new ClientePayload.Telefone();
				if (c.getTipo() != null) {
					telefone.setTipo(c.getTipo());
				}
				if (c.getNumero() != null) {
					telefone.setNumero(c.getNumero());
				}
				telefones.add(telefone);
			});
		}
		clientePayload.setTelefones(telefones);
		
		return clientePayload;		
	}

}
