package br.com.stapassoli.msloja.relatorio.impl;

import br.com.stapassoli.msloja.dto.VendaDTO;
import br.com.stapassoli.msloja.relatorio.BasicGeradorPDF;

import java.util.List;

public class RelatorioVenda extends BasicGeradorPDF<VendaDTO> {

    public RelatorioVenda(String nomeRelatorio,List<String> camposRelatorio, List<VendaDTO> dtos) {
        super(nomeRelatorio,camposRelatorio, dtos);
    }

    @Override
    public void gerarTabela() {

        this.dtos.forEach(dto ->{

            super.table.addCell(dto.getId().toString());
            super.table.addCell(dto.getValor().toString());
            super.table.addCell(dto.getStatusPagamento().name());
            super.table.addCell(dto.getCodigo_entrega() != null ? dto.getCodigo_entrega().toString() : "Sem código de rastreio");

        });

    }

}
