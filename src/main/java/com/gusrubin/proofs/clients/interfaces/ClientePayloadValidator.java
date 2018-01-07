package com.gusrubin.proofs.clients.interfaces;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;

import com.gusrubin.proofs.clients.domain.TelefoneType;

public class ClientePayloadValidator {
	
	private ClientePayloadValidator() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static void isCreationValid(ClientePayload clientePayload) {
		if (clientePayload == null) {
			throw new IllegalArgumentException("Faltando informações do cliente.");
		}
		if (clientePayload.getNome() == null || StringUtils.isEmpty(clientePayload.getNome())) {
			throw new IllegalArgumentException("Faltando informar nome do cliente.");
		}
		if (clientePayload.getCpf() == null || String.valueOf(clientePayload.getCpf()).length() != 11) {
			throw new IllegalArgumentException("CPF inválido ou não informado.");
		}
		if (clientePayload.getDataDeNascimento() == null || StringUtils.isEmpty(clientePayload.getDataDeNascimento())) {
			throw new IllegalArgumentException("Faltando informar a data de nascimento.");
		}
		if (!isDataNascimentoValid(clientePayload.getDataDeNascimento())) {
			throw new IllegalArgumentException("Formato da data de nascimento inválido, deve ser DD-MM-AAAA.");
		}
		if (clientePayload.getEndereco() == null) {
			throw new IllegalArgumentException("Faltando informações do endereço do cliente.");
		}
		if (clientePayload.getEndereco().getEstado() == null || StringUtils.isEmpty(clientePayload.getEndereco().getEstado())) {
			throw new IllegalArgumentException("Faltando informar o estado.");
		}
		if (clientePayload.getEndereco().getCidade() == null || StringUtils.isEmpty(clientePayload.getEndereco().getCidade())) {
			throw new IllegalArgumentException("Faltando informar a cidade.");
		}
		if (clientePayload.getEndereco().getRua() == null || StringUtils.isEmpty(clientePayload.getEndereco().getRua())) {
			throw new IllegalArgumentException("Faltando informar a rua.");
		}
		if (clientePayload.getTelefones() == null) {
			throw new IllegalArgumentException("Faltando informações de telefones do cliente.");
		}
		clientePayload.getTelefones().forEach(c -> {
			if (c.getTipo() == null || !isTelefoneValid(c.getTipo())) {
				throw new IllegalArgumentException("Tipo de telefone inválido ou não informado, deve ser FIXO ou CELULAR.");
			}
			if (c.getNumero() == null || String.valueOf(c.getNumero()).length() < 10) {
				throw new IllegalArgumentException("Número de telefone inválido ou não informado.");
			}
		});		
		
	}
	
	public static void isUpdateValid(ClientePayload clientePayload) {
		if (clientePayload != null) {
			if (clientePayload.getNome() != null) {
				if (StringUtils.isEmpty(clientePayload.getNome())) {
					throw new IllegalArgumentException("O nome do cliente não pode ser nulo.");
				}
			}
			if (clientePayload.getCpf() != null) {
				if (String.valueOf(clientePayload.getCpf()).length() != 11) {
					throw new IllegalArgumentException("CPF inválido.");
				}
			}
			if (clientePayload.getDataDeNascimento() != null) {
				if (StringUtils.isEmpty(clientePayload.getDataDeNascimento())) {
					throw new IllegalArgumentException("A data de nascimento não pode ser nula.");
				}
			}
		}
		
		if (clientePayload.getEndereco() != null) {
			if (clientePayload.getEndereco().getEstado() != null) {
				if (StringUtils.isEmpty(clientePayload.getEndereco().getEstado())) {
					throw new IllegalArgumentException("O estado não pode ser nulo.");
				}
			}
			if (clientePayload.getEndereco().getCidade() != null) {
				if (StringUtils.isEmpty(clientePayload.getEndereco().getCidade())) {
					throw new IllegalArgumentException("A cidade não pode ser nula.");
				}
			}
			if (clientePayload.getEndereco().getRua() != null) {
				if (StringUtils.isEmpty(clientePayload.getEndereco().getRua())) {
					throw new IllegalArgumentException("A rua não pode ser nula.");
				}
			}
		}
		
		if (clientePayload.getTelefones() != null) {
			clientePayload.getTelefones().forEach(c -> {
				if (c.getTipo() != null) {
					if (!isTelefoneValid(c.getTipo())) {
						throw new IllegalArgumentException("Tipo de telefone inválido, deve ser FIXO ou CELULAR.");
					}
				}
				if (c.getNumero() != null) {
					if (String.valueOf(c.getNumero()).length() < 10) {
						throw new IllegalArgumentException("Número de telefone inválido.");
					}
				}
			});			
		}
		
	}
	
	private static boolean isTelefoneValid(String tipoTelefone) {
		for (TelefoneType type : TelefoneType.values()) {
			if (tipoTelefone.equals(type.name())) {
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unused")
	private static boolean isDataNascimentoValid(String dataDeNascimento) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    dateFormat.setLenient(false);
	    try {
	    	dateFormat.parse(dataDeNascimento.trim());
	    } catch (ParseException pe) {
	    	return false;
	    }
	    return true;
	}

}
