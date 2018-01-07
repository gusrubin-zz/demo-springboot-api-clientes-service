package com.gusrubin.proofs.clients.infra;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gusrubin.proofs.clients.domain.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, String> {
	
	public Cliente findByCpf(@Param("cpf") String cpf);
	public Long countByCpf(@Param("cpf") String cpf);

}
