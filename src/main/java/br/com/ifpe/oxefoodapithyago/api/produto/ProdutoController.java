package br.com.ifpe.oxefoodapithyago.api.produto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.ifpe.oxefoodapithyago.modelo.categoriaproduto.CategoriaProdutoService;
import br.com.ifpe.oxefoodapithyago.modelo.produto.Produto;
import br.com.ifpe.oxefoodapithyago.modelo.produto.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produto")
@CrossOrigin
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaProdutoService categoriaProdutoService;

    @Operation(summary = "Serviço responsável por salvar um produto no sistema.")    
    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody @Valid ProdutoRequest request) {
        Produto produtoNovo = request.build();
        produtoNovo.setCategoria(categoriaProdutoService.obterPorID(request.getIdCategoria()));
        Produto produto = produtoService.save(produtoNovo);
        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Serviço responsável por listar todos os produtos do sistema.")
    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @Operation(summary = "Serviço responsável por listar um produto específico do sistema.")
    @GetMapping("/{id}")
    public Produto obterPorID(@PathVariable Long id) {
        return produtoService.obterPorID(id);
    }

    @Operation(summary = "Serviço responsável por alterar dados de um produto específico do sistema.")
    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable("id") Long id, @RequestBody ProdutoRequest request) {
        Produto produto = request.build();
        produto.setCategoria(categoriaProdutoService.obterPorID(request.getIdCategoria()));
        produtoService.update(id, produto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Serviço responsável por excluir um produto específico do sistema.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Serviço responsável por filtrar produtos no sistema.")
    @PostMapping("/filtrar")
   public List<Produto> filtrar(
           @RequestParam(value = "codigo", required = false) String codigo,
           @RequestParam(value = "titulo", required = false) String titulo,
           @RequestParam(value = "idCategoria", required = false) Long idCategoria) {

       return produtoService.filtrar(codigo, titulo, idCategoria);
   }

}
