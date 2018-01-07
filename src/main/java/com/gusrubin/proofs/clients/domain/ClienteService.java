package com.gusrubin.proofs.clients.domain;

import java.util.List;

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
		if (clienteRepository.countByCpf(novoCliente.getCpf()) != 0) {
			throw new IllegalStateException("CPF de cliente já cadastrado.");
		}
		enderecoRepository.save(novoCliente.getEndereco());
		telefoneRepository.save(novoCliente.getTelefones());
		return clienteRepository.save(novoCliente);
	}
	
	public Cliente update(Cliente clienteAtualizado, String clienteId) {
		Cliente cliente = clienteRepository.findOne(clienteId);
		if (clienteAtualizado.getNome() != null) {
			cliente.setNome(clienteAtualizado.getNome());
		}
		if (clienteAtualizado.getCpf() != null) {
			if (!clienteAtualizado.getCpf().equals(cliente.getCpf())) {
				throw new IllegalStateException("Não é permitida a atualização de CPF.");
			}
		}
		if (clienteAtualizado.getDataDeNascimento() != null) {
			cliente.setDataDeNascimento(clienteAtualizado.getDataDeNascimento());
		}
		if (clienteAtualizado.getEndereco() != null) {
			Endereco endereco = enderecoRepository.findOne(cliente.getEndereco().getId());
			if (clienteAtualizado.getEndereco().getEstado() != null) {
				endereco.setEstado(clienteAtualizado.getEndereco().getEstado());
			}
			if (clienteAtualizado.getEndereco().getCidade() != null) {
				endereco.setCidade(clienteAtualizado.getEndereco().getCidade());
			}
			if (clienteAtualizado.getEndereco().getRua() != null) {
				endereco.setRua(clienteAtualizado.getEndereco().getRua());
			}
			if (clienteAtualizado.getEndereco().getNumero() != null) {
				endereco.setNumero(clienteAtualizado.getEndereco().getNumero());
			}
			if (clienteAtualizado.getEndereco().getComplemento() != null) {
				endereco.setComplemento(clienteAtualizado.getEndereco().getComplemento());
			}
			if (clienteAtualizado.getEndereco().getCep() != null) {
				endereco.setCep(clienteAtualizado.getEndereco().getCep());
			}
			enderecoRepository.save(endereco);
		}
		if (clienteAtualizado.getTelefones() != null) {
			clienteAtualizado.getTelefones().forEach(c -> {
				List<Telefone> telefones = telefoneRepository.findByNumero(c.getNumero());
				telefones.forEach(t -> {					
					if (!t.getNumero().equals(c.getNumero())) {
						t.setNumero(c.getNumero());					
					}
					telefoneRepository.save(t);
				});
				
			});
		}
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
