/**
* Interface de repositório da entidade Endereco no modelo de dados.
* @version 1.0
* @author Gustavo Rubin
*/

package com.gusrubin.proofs.clients.infra;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gusrubin.proofs.clients.domain.Endereco;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

}
