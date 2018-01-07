package com.gusrubin.proofs.clients.interfaces;

public class Paths {
	
	public static final String CLIENTE_BASE_PATH = "/clientes";
	
	public static final String CLIENTE_ID_PATH_VARIABLE_NAME = "clienteId";
	
	public static final String CLIENTE_ID_PATH_VARIABLE = "{"+ CLIENTE_ID_PATH_VARIABLE_NAME +"}";
	
	public static final String CLIENTE_SUB_RESOURCE_BASE_PATH = CLIENTE_BASE_PATH + "/" + CLIENTE_ID_PATH_VARIABLE;

}
