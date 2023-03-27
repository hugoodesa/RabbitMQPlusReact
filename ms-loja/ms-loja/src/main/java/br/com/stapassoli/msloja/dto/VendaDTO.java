package br.com.stapassoli.msloja.dto;

import br.com.stapassoli.msloja.enums.StatusPagamento;
import br.com.stapassoli.msloja.model.Venda;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class VendaDTO implements IDTO<Venda> {

    private Long id;

    private BigDecimal valor;

    private StatusPagamento statusPagamento;
    private Long codigo_entrega;

    public Venda toEntity(){
        return Venda
        .builder()
        .id(this.id)
        .valor(this.valor)
        .statusPagamento(this.statusPagamento)
        .codigo_entrega(codigo_entrega)
        .build();
    }

}
