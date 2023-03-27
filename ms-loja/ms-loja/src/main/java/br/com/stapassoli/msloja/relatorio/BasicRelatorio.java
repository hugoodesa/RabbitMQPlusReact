package br.com.stapassoli.msloja.relatorio;

import br.com.stapassoli.msloja.dto.IDTO;

import java.util.List;

public interface BasicRelatorio {

    void header(List<String> colunas);

    void body(List<IDTO> listaDTO);

}
