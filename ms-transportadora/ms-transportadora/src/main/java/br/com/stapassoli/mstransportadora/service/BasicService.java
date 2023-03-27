package br.com.stapassoli.mstransportadora.service;

import br.com.stapassoli.mstransportadora.dto.IDTO;
import br.com.stapassoli.mstransportadora.model.IEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BasicService<Repository extends JpaRepository<IEntity, Long>> {

    public Repository repository;

    public BasicService(Repository repository) {
        this.repository = repository;
    }

    public ResponseEntity post(IDTO<IEntity> entityDTO) {
        IEntity entity = this.repository.save(entityDTO.toEntity());
        return ResponseEntity.ok(entity);
    }

    public Page<Object> listaAll(Pageable pageable) {
        List<Object> objects = this.repository.findAll(pageable).stream().map(IEntity::toDTO).toList();
        return new PageImpl<>(objects);
    }

    @Transactional
    public ResponseEntity getById(Long id) {
        IEntity entity = this.repository.getReferenceById(id);
        return ResponseEntity.ok(entity.toDTO());
    }

    @Transactional
    public ResponseEntity deleteByID(Long id) {
        this.repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    public ResponseEntity putById(Long id, IDTO dto) {
        IEntity entity = this.repository.getReferenceById(id);
        entity.update(dto);
        var newEntity = this.repository.save(entity);
        return ResponseEntity.ok(newEntity.toDTO());
    }


}
