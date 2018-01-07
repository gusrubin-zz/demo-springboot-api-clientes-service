package com.gusrubin.proofs.clients.infra;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gusrubin.proofs.clients.domain.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, String> {
	
	public Cliente findByCpf(String cpf);

}
