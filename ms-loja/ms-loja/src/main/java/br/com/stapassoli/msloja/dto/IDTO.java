package br.com.stapassoli.msloja.dto;

public interface IDTO<Entity> {

    abstract Entity toEntity();
}
