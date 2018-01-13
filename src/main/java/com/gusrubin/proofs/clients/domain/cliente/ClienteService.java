/**
* Classe service da entidade Cliente,
* responsável pelas regras de negócio dessa entidade.
* @version 1.0
* @author Gustavo Rubin
*/

package com.gusrubin.proofs.clients.domain.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gusrubin.proofs.clients.domain.endereco.Endereco;
import com.gusrubin.proofs.clients.domain.telefone.Telefone;
import com.gusrubin.proofs.clients.infra.repositories.ClienteRepository;
import com.gusrubin.proofs.clients.infra.repositories.EnderecoRepository;
import com.gusrubin.proofs.clients.infra.repositories.TelefoneRepository;
import com.gusrubin.proofs.clients.interfaces.commons.ExceptionErrorMessages;

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
			throw new IllegalStateException(ExceptionErrorMessages.ERROR_422_CLIENT_CPF_ALREADY_REGISTERED);
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
				throw new IllegalStateException(ExceptionErrorMessages.ERROR_422_CLIENT_CPF_CAN_NOT_BE_UPDATED);
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
			throw new IllegalStateException(ExceptionErrorMessages.ERROR_422_CLIENT_DO_NOT_EXIST);
		}
		return cliente;
	}
	
	public void delete(String clienteId) {
		Cliente cliente = clienteRepository.findOne(clienteId);
		if (cliente == null) {
			throw new IllegalStateException(ExceptionErrorMessages.ERROR_422_CLIENT_DO_NOT_EXIST);
		}
		clienteRepository.delete(cliente);
	}

}
