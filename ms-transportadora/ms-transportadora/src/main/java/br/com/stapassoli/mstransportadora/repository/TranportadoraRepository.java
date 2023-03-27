package br.com.stapassoli.mstransportadora.repository;

import br.com.stapassoli.mstransportadora.model.impl.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranportadoraRepository extends JpaRepository<Entrega,Long> {
}
