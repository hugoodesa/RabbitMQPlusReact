package br.com.stapassoli.mstransportadora.controller;

import br.com.stapassoli.mstransportadora.dto.impl.EntregaDTO;
import br.com.stapassoli.mstransportadora.service.impl.TranportadoraService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transportadora")
@CrossOrigin
public class TranportadoraController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    TranportadoraService service;

    @GetMapping("/teste")
    public ResponseEntity<String> mainMethod(){
        return ResponseEntity.ok("Transportadora method");
    }

    @GetMapping("/{id}")
    public ResponseEntity getEntrega(@PathVariable Long id){
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEntrega(@PathVariable Long id){
        return service.deleteByID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarEntrega(@PathVariable Long id, @RequestBody EntregaDTO entrega){
        rabbitTemplate.convertAndSend("fila-entrega",entrega);
        return service.putById(id,entrega);
    }

    @PostMapping
    public ResponseEntity cadastrarEntrega(@RequestBody EntregaDTO dto){
        rabbitTemplate.convertAndSend("fila-entrega",dto);
        return service.post(dto);
    }

    @GetMapping
    public Page<EntregaDTO> getAll(@PageableDefault(size = 10) Pageable pageable){
        return service.listaAll(pageable);
    }

}
