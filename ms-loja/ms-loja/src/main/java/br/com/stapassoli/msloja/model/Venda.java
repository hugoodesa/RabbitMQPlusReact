package br.com.stapassoli.msloja.model;

import br.com.stapassoli.msloja.dto.IDTO;
import br.com.stapassoli.msloja.dto.VendaDTO;
import br.com.stapassoli.msloja.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "vendas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Venda implements IEntity<VendaDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    private Long codigo_entrega;

    @Override
    public VendaDTO toDTO() {
        return VendaDTO
                .builder()
                .id(this.id)
                .valor(this.valor)
                .codigo_entrega(this.codigo_entrega)
                .statusPagamento(this.statusPagamento)
                .build();
    }

    @Override
    public void update(VendaDTO dto) {

        if(dto.getId()!=null){
            this.id = dto.getId();
        }

        this.valor = dto.getValor();
        this.codigo_entrega = dto.getCodigo_entrega();
        this.statusPagamento = dto.getStatusPagamento();
    }
}
