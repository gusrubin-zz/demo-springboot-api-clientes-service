package com.gusrubin.proofs.clients.infra;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gusrubin.proofs.clients.domain.Endereco;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

}
