package br.com.stapassoli.msloja.repository;

import br.com.stapassoli.msloja.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query(value = "select * from vendas v where v.codigo_entrega is not null" ,nativeQuery = true)
    List<Venda> buscarEntregasComRastreio();

}
