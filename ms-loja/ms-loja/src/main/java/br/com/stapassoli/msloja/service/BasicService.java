package br.com.stapassoli.msloja.service;

import br.com.stapassoli.msloja.dto.IDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BasicService<Entity,DTO> {

    ResponseEntity post(IDTO<Entity> entityDTO);

    Page<DTO> listaAll(Pageable pageable);

    ResponseEntity getById(Long id);

    ResponseEntity deleteByID(Long id);

    ResponseEntity putById(Long id, DTO dto);

}
