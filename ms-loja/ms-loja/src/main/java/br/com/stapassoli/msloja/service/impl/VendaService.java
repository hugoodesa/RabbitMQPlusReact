package br.com.stapassoli.msloja.service.impl;

import br.com.stapassoli.msloja.dto.IDTO;
import br.com.stapassoli.msloja.dto.VendaDTO;
import br.com.stapassoli.msloja.model.Venda;
import br.com.stapassoli.msloja.repository.VendaRepository;
import br.com.stapassoli.msloja.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VendaService implements BasicService<Venda,VendaDTO> {

    @Autowired
    VendaRepository repository;

    @Transactional
    @Override
    public ResponseEntity post(IDTO<Venda> entityDTO) {
        var venda = this.repository.save(entityDTO.toEntity());
        return ResponseEntity.ok(venda.toDTO());
    }

    @Override
    public Page<VendaDTO> listaAll(Pageable pageable) {
        List<VendaDTO> vendaDTOS = this.repository.findAll(pageable).map(Venda::toDTO).toList();
        return new PageImpl<>(vendaDTOS);
    }


    @Override
    public ResponseEntity getById(Long id) {
        var venda = this.repository.getReferenceById(id);
        return ResponseEntity.ok(venda.toDTO());
    }

    @Transactional
    @Override
    public ResponseEntity deleteByID(Long id) {
        this.repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @Override
    public ResponseEntity putById(Long id, VendaDTO dto) {
        var venda = this.repository.getReferenceById(id);
        venda.update(dto);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity getVendasComEntrega(){
        List<VendaDTO> vendaDTOS = this.repository.buscarEntregasComRastreio().stream().map(Venda::toDTO).toList();
        return ResponseEntity.ok(vendaDTOS);
    }

    public ResponseEntity<List<VendaDTO>> buscarTodasVendas (){
        List<VendaDTO> vendaDTOS = this.repository.findAll().stream().map(Venda::toDTO).toList();
        return ResponseEntity.ok(vendaDTOS);
    }

}
