package com.gusrubin.proofs.clients.interfaces.commons;

public class ExceptionErrorMessages {
	
	private ExceptionErrorMessages() {
	    throw new IllegalStateException("Utility class");
	}
	
	/**** 400 messages ****/	
	public static final String ERROR_400_CLIENT_INFORMATION_IS_MISSING = "Faltando informações do cliente.";
	public static final String ERROR_400_CLIENT_NAME_IS_INVALID = "Faltando informar nome do cliente.";
	public static final String ERROR_400_CLIENT_CPF_IS_INVALID = "CPF inválido ou não informado.";
	public static final String ERROR_400_CLIENT_BIRTH_DATE_IS_INVALID = "Faltando informar a data de nascimento.";
	public static final String ERROR_400_CLIENT_BIRTH_DATE_FORMAT_IS_INVALID = "Formato da data de nascimento inválido, deve ser DD-MM-AAAA.";
	public static final String ERROR_400_CLIENT_ADDRESS_IS_INVALID = "Faltando informações do endereço do cliente.";
	public static final String ERROR_400_CLIENT_ADDRESS_STATE_IS_MISSING = "Faltando informar o estado.";
	public static final String ERROR_400_CLIENT_ADDRESS_CITYE_IS_MISSING = "Faltando informar a cidade.";
	public static final String ERROR_400_CLIENT_ADDRESS_STREET_IS_MISSING = "Faltando informar a rua.";
	public static final String ERROR_400_CLIENT_TELEPHONE_IS_MISSING = "Faltando informações de telefones do cliente.";
	public static final String ERROR_400_CLIENT_TELEPHONE_TYPE_IS_INVALID = "Tipo de telefone inválido ou não informado, deve ser FIXO ou CELULAR.";
	public static final String ERROR_400_CLIENT_TELEPHONE_NUMBER_IS_INVALID = "Número de telefone inválido ou não informado.";
	
	/**** 422 messages ****/	
	public static final String ERROR_422_CLIENT_CPF_ALREADY_REGISTERED = "CPF de cliente já cadastrado.";
	public static final String ERROR_422_CLIENT_CPF_CAN_NOT_BE_UPDATED = "Não é permitida a atualização de CPF.";
	public static final String ERROR_422_CLIENT_DO_NOT_EXIST = "Cliente não existe";

}
