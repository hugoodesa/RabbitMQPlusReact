package br.com.stapassoli.mstransportadora.dto.impl;

import br.com.stapassoli.mstransportadora.enums.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VendaDTO {

    private Long id;

    private BigDecimal valor;

    private StatusPagamento statusPagamento;
    private Long codigo_entrega;

}
