package br.com.stapassoli.msloja.relatorio.impl;

import br.com.stapassoli.msloja.dto.VendaDTO;
import br.com.stapassoli.msloja.enums.StatusPagamento;

import java.math.BigDecimal;
import java.util.List;

public class ClasseTeste {

    public static void main(String[] args) {

        List<String> colunas = List.of("id", "valor", "statusPagamento", "codigo_entrega");

        List<VendaDTO> vendaDTOBuilders = List.of(
                VendaDTO.builder().id(1L).valor(new BigDecimal(1000)).codigo_entrega(1L).statusPagamento(StatusPagamento.PEDIDO_REALIZADO).build(),
                VendaDTO.builder().id(2L).valor(new BigDecimal(2000)).codigo_entrega(2L).statusPagamento(StatusPagamento.PEDIDO_REALIZADO).build()
        );

        //RelatorioVenda relatorioVenda = new RelatorioVenda(colunas, vendaDTOBuilders);
        //relatorioVenda.GerarRelatorio();

    }

}
