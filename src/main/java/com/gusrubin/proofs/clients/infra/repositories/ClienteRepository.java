/**
* Interface de repositório da entidade Cliente no modelo de dados.
* @version 1.0
* @author Gustavo Rubin
*/

package com.gusrubin.proofs.clients.infra.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gusrubin.proofs.clients.domain.cliente.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, String> {
	
	public Cliente findByCpf(@Param("cpf") String cpf);
	public Long countByCpf(@Param("cpf") String cpf);

}
