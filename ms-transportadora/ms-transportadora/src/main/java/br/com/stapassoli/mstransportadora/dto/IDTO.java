package br.com.stapassoli.mstransportadora.dto;

public interface IDTO<Entity> {

    abstract Entity toEntity();
}
