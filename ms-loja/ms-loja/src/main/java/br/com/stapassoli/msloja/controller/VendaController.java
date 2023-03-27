package br.com.stapassoli.msloja.controller;

import br.com.stapassoli.msloja.dto.VendaDTO;
import br.com.stapassoli.msloja.enums.StatusPagamento;
import br.com.stapassoli.msloja.relatorio.impl.RelatorioVenda;
import br.com.stapassoli.msloja.service.impl.VendaService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/venda")
@CrossOrigin
public class VendaController {

    @Autowired
    RabbitTemplate template;

    @Autowired
    VendaService service;

    @GetMapping
    public Page<VendaDTO> mainMethod (@PageableDefault(size = 10,direction = Sort.Direction.ASC) Pageable pageble){
        return service.listaAll(pageble);
    }

    @GetMapping("/{id}")
    public ResponseEntity mainMethod (@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity cadastrarVenda (@RequestBody VendaDTO dto){
        ResponseEntity post = service.post(dto);

        VendaDTO venda = (VendaDTO) post.getBody();

        template.convertAndSend("fila-venda",venda);

        return post;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        return this.service.deleteByID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id,@RequestBody VendaDTO vendaDTO){

        if(vendaDTO.getStatusPagamento().equals(StatusPagamento.CANCELADO)){
            template.convertAndSend("fila-venda",vendaDTO);
        }

        return this.service.putById(id,vendaDTO);
    }

    @GetMapping("/vendasComEntrega")
    public ResponseEntity buscarVendasComEntrega(){
        return this.service.getVendasComEntrega();
    }

    @GetMapping("/buscarTodasVendas")
    public ResponseEntity buscarTodasVendas(){
        return this.service.buscarTodasVendas();
    }

    @GetMapping("/gerarRelatorio")
    public ResponseEntity<byte[]> gerarRelatorio() throws IOException {
        ResponseEntity<List<VendaDTO>> listResponseEntity = this.service.buscarTodasVendas();
        RelatorioVenda relatorioVenda = new RelatorioVenda("relatorio001.pdf",List.of("id", "valor", "statusPagamento", "codigo_entrega"), listResponseEntity.getBody());

        relatorioVenda.GerarRelatorio();

        byte[] contents = Files.readAllBytes(Paths.get("C:/dev/fullProject/relatorio001.pdf"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = "relatorio001.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);

        return response;

        //return null;
    }

}
