package br.com.stapassoli.msloja.rabbit.listner;

import br.com.stapassoli.msloja.dto.VendaDTO;
import br.com.stapassoli.msloja.service.impl.VendaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntregaListner {

    @Autowired
    VendaService service;

    @RabbitListener(queues = "fila-entrega")
    public void escutarFilaEntrega(VendaDTO dto){

        System.out.println("========= inicio cadastro ========");
        service.putById(dto.getId(), dto);
        System.out.println("========= final cadastro ========");

        System.out.println("====");
        System.out.println(dto);
        System.out.println("====");
    }

}
