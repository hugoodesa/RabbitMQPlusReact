package br.com.stapassoli.mstransportadora.model;

public interface IEntity<DTO> {

    DTO toDTO();
    void update(DTO dto);

}
