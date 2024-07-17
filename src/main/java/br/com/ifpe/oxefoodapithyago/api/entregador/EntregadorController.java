package br.com.ifpe.oxefoodapithyago.api.entregador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefoodapithyago.modelo.entregador.Entregador;
import br.com.ifpe.oxefoodapithyago.modelo.entregador.EntregadorService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/entregador")
@CrossOrigin
public class EntregadorController {

    @Autowired
    private EntregadorService EntregadorService;

     @Operation(
       summary = "Serviço responsável por salvar um entregador no sistema."
   )

    @PostMapping
    public ResponseEntity<Entregador> save(@RequestBody EntregadorRequest request) {

        Entregador entregador = EntregadorService.save(request.build());
        return new ResponseEntity<Entregador>(entregador, HttpStatus.CREATED);
    }

    @Operation(
       summary = "Serviço responsável por listar todos os entregadores do sistema."
   )
    @GetMapping
    public List<Entregador> listarTodos() {
        return EntregadorService.listarTodos();
    }

    @Operation(
       summary = "Serviço responsável por listar um entregador especifico do sistema."
   )

    @GetMapping("/{id}")
    public Entregador obterPorID(@PathVariable Long id) {
        return EntregadorService.obterPorID(id);
    }

    @Operation(
       summary = "Serviço responsável por alterar dados de um entregador especifico do sistema."
   )

    @PutMapping("/{id}")
    public ResponseEntity<Entregador> update(@PathVariable("id") Long id, @RequestBody EntregadorRequest request) {

        EntregadorService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

    @Operation(
       summary = "Serviço responsável por excluir um entregador especifico do sistema."
   )

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        EntregadorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
