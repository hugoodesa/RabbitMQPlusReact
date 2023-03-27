package br.com.stapassoli.msloja.dto;

import br.com.stapassoli.msloja.enums.StatusEntrega;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class EntregaDTO {

    private Long id;
    private StatusEntrega statusEntrega;

}
