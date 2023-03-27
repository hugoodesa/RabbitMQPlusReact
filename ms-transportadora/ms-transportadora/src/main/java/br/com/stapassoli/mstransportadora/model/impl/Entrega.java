package br.com.stapassoli.mstransportadora.model.impl;

import br.com.stapassoli.mstransportadora.dto.impl.EntregaDTO;
import br.com.stapassoli.mstransportadora.enums.StatusEntrega;
import br.com.stapassoli.mstransportadora.model.IEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "entregas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Entrega implements IEntity<EntregaDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusEntrega statusEntrega;

    @Override
    public EntregaDTO toDTO() {
        return EntregaDTO.
                builder().
                id(this.id)
                .statusEntrega(this.statusEntrega)
                .build();
    }

    @Override
    public void update(EntregaDTO entregaDTO) {
        this.statusEntrega = entregaDTO.getStatusEntrega();
    }
}
