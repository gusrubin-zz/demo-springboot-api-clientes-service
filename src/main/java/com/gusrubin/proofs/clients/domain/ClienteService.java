package com.gusrubin.proofs.clients.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gusrubin.proofs.clients.infra.ClienteRepository;
import com.gusrubin.proofs.clients.infra.EnderecoRepository;
import com.gusrubin.proofs.clients.infra.TelefoneRepository;

@Service
public class ClienteService {
	
	private final ClienteRepository clienteRepository;
	private final EnderecoRepository enderecoRepository;
	private final TelefoneRepository telefoneRepository;
	
	@Autowired
	public ClienteService(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository,
			TelefoneRepository telefoneRepository) {
		super();
		this.clienteRepository = clienteRepository;
		this.enderecoRepository = enderecoRepository;
		this.telefoneRepository = telefoneRepository;
	}
	
	public Cliente create(Cliente novoCliente) {
		Cliente cliente = clienteRepository.findByCpf(novoCliente.getCpf());
		if (cliente != null) {
			throw new IllegalStateException("CPF de cliente já cadastrado.");
		}
		return clienteRepository.save(novoCliente);
	}
	
	public Cliente update(Cliente clienteAtualizado) {
		Cliente cliente = clienteRepository.findOne(clienteAtualizado.getId());
		if (!clienteAtualizado.getClass().equals(cliente.getCpf())) {
			throw new IllegalStateException("Não é permitida a atualização de CPF.");
		}
		cliente = clienteAtualizado;
		return clienteRepository.save(cliente);
	}
	
	public Cliente get(String clienteId) {
		Cliente cliente = clienteRepository.findOne(clienteId);
		if (cliente == null) {
			throw new IllegalStateException("Cliente não cadastrado.");
		}
		return cliente;
	}
	
	public void delete(String clienteId) {
		Cliente cliente = clienteRepository.findOne(clienteId);
		if (cliente == null) {
			throw new IllegalStateException("Cliente não cadastrado.");
		}
		clienteRepository.delete(cliente);
	}

}
