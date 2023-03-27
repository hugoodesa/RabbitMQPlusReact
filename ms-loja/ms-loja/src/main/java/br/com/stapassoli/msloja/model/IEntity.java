package br.com.stapassoli.msloja.model;

public interface IEntity<DTO> {

    DTO toDTO();
    void update(DTO dto);

}
