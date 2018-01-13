/**
* Classe de validação do payload do recurso Cliente da API REST.
* @version 1.0
* @author Gustavo Rubin
*/

package com.gusrubin.proofs.clients.interfaces.cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;

import com.gusrubin.proofs.clients.domain.telefone.TelefoneType;
import com.gusrubin.proofs.clients.interfaces.commons.ExceptionErrorMessages;

public class ClientePayloadValidator {
	
	private ClientePayloadValidator() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static void isCreationValid(ClientePayload clientePayload) {
		if (clientePayload == null) {
			throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_INFORMATION_IS_MISSING);
		}
		if (clientePayload.getNome() == null || StringUtils.isEmpty(clientePayload.getNome())) {
			throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_NAME_IS_INVALID);
		}
		if (clientePayload.getCpf() == null || String.valueOf(clientePayload.getCpf()).length() != 11) {
			throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_CPF_IS_INVALID);
		}
		if (clientePayload.getDataDeNascimento() == null || StringUtils.isEmpty(clientePayload.getDataDeNascimento())) {
			throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_BIRTH_DATE_IS_INVALID);
		}
		if (!isDataNascimentoValid(clientePayload.getDataDeNascimento())) {
			throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_BIRTH_DATE_FORMAT_IS_INVALID);
		}
		if (clientePayload.getEndereco() == null) {
			throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_ADDRESS_IS_INVALID);
		}
		if (clientePayload.getEndereco().getEstado() == null || StringUtils.isEmpty(clientePayload.getEndereco().getEstado())) {
			throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_ADDRESS_STATE_IS_MISSING);
		}
		if (clientePayload.getEndereco().getCidade() == null || StringUtils.isEmpty(clientePayload.getEndereco().getCidade())) {
			throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_ADDRESS_CITYE_IS_MISSING);
		}
		if (clientePayload.getEndereco().getRua() == null || StringUtils.isEmpty(clientePayload.getEndereco().getRua())) {
			throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_ADDRESS_STREET_IS_MISSING);
		}
		if (clientePayload.getTelefones() == null) {
			throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_TELEPHONE_IS_MISSING);
		}
		clientePayload.getTelefones().forEach(c -> {
			if (c.getTipo() == null || !isTelefoneValid(c.getTipo())) {
				throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_TELEPHONE_TYPE_IS_INVALID);
			}
			if (c.getNumero() == null || String.valueOf(c.getNumero()).length() < 10) {
				throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_TELEPHONE_NUMBER_IS_INVALID);
			}
		});		
		
	}
	
	public static void isUpdateValid(ClientePayload clientePayload) {
		if (clientePayload != null) {
			if (clientePayload.getNome() != null) {
				if (StringUtils.isEmpty(clientePayload.getNome())) {
					throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_NAME_IS_INVALID);
				}
			}
			if (clientePayload.getCpf() != null) {
				if (String.valueOf(clientePayload.getCpf()).length() != 11) {
					throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_CPF_IS_INVALID);
				}
			}
			if (clientePayload.getDataDeNascimento() != null) {
				if (StringUtils.isEmpty(clientePayload.getDataDeNascimento())) {
					throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_BIRTH_DATE_IS_INVALID);
				}
			}
		}
		
		if (clientePayload.getEndereco() != null) {
			if (clientePayload.getEndereco().getEstado() != null) {
				if (StringUtils.isEmpty(clientePayload.getEndereco().getEstado())) {
					throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_ADDRESS_STATE_IS_MISSING);
				}
			}
			if (clientePayload.getEndereco().getCidade() != null) {
				if (StringUtils.isEmpty(clientePayload.getEndereco().getCidade())) {
					throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_ADDRESS_CITYE_IS_MISSING);
				}
			}
			if (clientePayload.getEndereco().getRua() != null) {
				if (StringUtils.isEmpty(clientePayload.getEndereco().getRua())) {
					throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_ADDRESS_STREET_IS_MISSING);
				}
			}
		}
		
		if (clientePayload.getTelefones() != null) {
			clientePayload.getTelefones().forEach(c -> {
				if (c.getTipo() != null) {
					if (!isTelefoneValid(c.getTipo())) {
						throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_TELEPHONE_TYPE_IS_INVALID);
					}
				}
				if (c.getNumero() != null) {
					if (String.valueOf(c.getNumero()).length() < 10) {
						throw new IllegalArgumentException(ExceptionErrorMessages.ERROR_400_CLIENT_TELEPHONE_NUMBER_IS_INVALID);
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
