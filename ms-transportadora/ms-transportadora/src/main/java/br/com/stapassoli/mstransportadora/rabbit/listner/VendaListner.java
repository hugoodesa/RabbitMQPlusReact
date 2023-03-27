package br.com.stapassoli.mstransportadora.rabbit.listner;

import br.com.stapassoli.mstransportadora.dto.impl.VendaDTO;
import br.com.stapassoli.mstransportadora.enums.StatusEntrega;
import br.com.stapassoli.mstransportadora.model.impl.Entrega;
import br.com.stapassoli.mstransportadora.service.impl.TranportadoraService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class VendaListner {

    @Autowired
    TranportadoraService service;

    @Autowired
    RabbitTemplate template;

    @RabbitListener(queues = "fila-venda")
    public void listnerVenda (VendaDTO venda){

        Entrega entregaDTOBuilder = Entrega.builder().statusEntrega(StatusEntrega.EM_TRANSITO).build();
        ResponseEntity entrega = service.post(entregaDTOBuilder.toDTO());
        Entrega entidadeEntrega = (Entrega) entrega.getBody();

        venda.setCodigo_entrega(entidadeEntrega.getId());

        template.convertAndSend("fila-entrega",venda);

        System.out.println("===");
        System.out.println(venda);
        System.out.println("===");
    }

}
