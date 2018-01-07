package com.gusrubin.proofs.clients.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gusrubin.proofs.clients.domain.ClienteService;

@RestController
public class ClienteController {
	
	private final ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		super();
		this.clienteService = clienteService;
	}
	
	@RequestMapping(path = Paths.CLIENTE_BASE_PATH, method = RequestMethod.POST, consumes = InterfacesCommons.APPLICATION_JSON)
    public ResponseEntity<?> postCliente(@RequestBody ClientePayload requestBody, UriComponentsBuilder uriBuilder) {
        
        ClientePayloadValidator.isCreationValid(requestBody);
        
        String clienteId = clienteService.create(ClientePayloadConverter.toDomain(requestBody)).getId();        

        return ResponseEntity.created(uriBuilder.path(Paths.CLIENTE_SUB_RESOURCE_BASE_PATH).buildAndExpand(clienteId).toUri()).build();
    }
	
	@RequestMapping(path = Paths.CLIENTE_SUB_RESOURCE_BASE_PATH, method = RequestMethod.PATCH, consumes = InterfacesCommons.APPLICATION_JSON)
    public ResponseEntity<?> patchCliente(@RequestBody ClientePayload requestBody, 
    		@PathVariable(Paths.CLIENTE_ID_PATH_VARIABLE_NAME) String clienteId) {
        
        ClientePayloadValidator.isUpdateValid(requestBody);
        
        clienteService.update(ClientePayloadConverter.toDomain(requestBody), clienteId);        

        return ResponseEntity.noContent().build();
    }
	
	@RequestMapping(path = Paths.CLIENTE_SUB_RESOURCE_BASE_PATH, method = RequestMethod.GET, produces = InterfacesCommons.APPLICATION_JSON)
    public ResponseEntity<?> getCliente(@PathVariable(Paths.CLIENTE_ID_PATH_VARIABLE_NAME) String clienteId) {
        
        ClientePayload responseBody = ClientePayloadConverter.toPayload(clienteService.get(clienteId));  

        return ResponseEntity.ok(responseBody);
    }
	
	@RequestMapping(path = Paths.CLIENTE_SUB_RESOURCE_BASE_PATH, method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCliente(@PathVariable(Paths.CLIENTE_ID_PATH_VARIABLE_NAME) String clienteId) {
        
		clienteService.delete(clienteId);

        return ResponseEntity.noContent().build();
    }

}
