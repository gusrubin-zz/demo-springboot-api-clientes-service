/**
* Classe de conversão do payload do recurso Cliente da API REST para entidade Cliente do banco de dados e vice-versa.
* @version 1.0
* @author Gustavo Rubin
*/

package com.gusrubin.proofs.clients.interfaces.cliente;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gusrubin.proofs.clients.domain.cliente.Cliente;
import com.gusrubin.proofs.clients.domain.endereco.Endereco;
import com.gusrubin.proofs.clients.domain.telefone.Telefone;

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
				try {
					SimpleDateFormat dataNascimento = new SimpleDateFormat("dd-MM-yyyy");
					Date dataFormatada = dataNascimento.parse(clientePayload.getDataDeNascimento());
					cliente.setDataDeNascimento(new Timestamp(dataFormatada.getTime()));
				} catch (ParseException pe) {
					throw new IllegalArgumentException("Erro na conversão do formato da data de nascimento.");
				}
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
			if (clientePayload.getEndereco().getNumero() != null) {
				endereco.setNumero(clientePayload.getEndereco().getNumero());
			}
			if (clientePayload.getEndereco().getComplemento() != null) {
				endereco.setComplemento(clientePayload.getEndereco().getComplemento());
			}
			if (clientePayload.getEndereco().getCep() != null) {
				endereco.setCep(clientePayload.getEndereco().getCep());
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
			Date date = new Date();
			date.setTime(cliente.getDataDeNascimento().getTime());
			String formattedDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
			clientePayload.setDataDeNascimento(formattedDate);
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

		clientePayload.setTelefones(null);
		
		return clientePayload;		
	}

}
