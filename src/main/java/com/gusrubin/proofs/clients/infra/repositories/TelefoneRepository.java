/**
* Interface de repositório da entidade Telefone no modelo de dados.
* @version 1.0
* @author Gustavo Rubin
*/

package com.gusrubin.proofs.clients.infra.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gusrubin.proofs.clients.domain.telefone.Telefone;

@Repository
public interface TelefoneRepository extends CrudRepository<Telefone, Long> {
	
	public List<Telefone> findByNumero(String numero);

}
