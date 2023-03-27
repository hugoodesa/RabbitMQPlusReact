package br.com.stapassoli.mstransportadora.dto.impl;

import br.com.stapassoli.mstransportadora.dto.IDTO;
import br.com.stapassoli.mstransportadora.enums.StatusEntrega;
import br.com.stapassoli.mstransportadora.model.impl.Entrega;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EntregaDTO implements IDTO<Entrega> {

    private Long id;
    private StatusEntrega statusEntrega;

    @Override
    public Entrega toEntity() {
        return Entrega.builder().statusEntrega(this.statusEntrega).build();
    }
}
