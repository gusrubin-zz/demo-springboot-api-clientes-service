package com.gusrubin.proofs.clients.infra;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gusrubin.proofs.clients.domain.Telefone;

@Repository
public interface TelefoneRepository extends CrudRepository<Telefone, Long> {
	
	public List<Telefone> findByNumero(String numero);

}
