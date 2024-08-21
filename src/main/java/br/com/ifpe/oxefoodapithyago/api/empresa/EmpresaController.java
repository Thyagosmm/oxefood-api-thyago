package br.com.ifpe.oxefoodapithyago.api.empresa;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefoodapithyago.modelo.acesso.Usuario;
import br.com.ifpe.oxefoodapithyago.modelo.empresa.Empresa;
import br.com.ifpe.oxefoodapithyago.modelo.empresa.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/empresa")
@CrossOrigin
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Operation(summary = "Serviço responsável por salvar uma empresa no sistema.")

    @PostMapping
    public ResponseEntity<Empresa> save(@RequestBody EmpresaRequest request) {

        Empresa empresa = request.build();

		if (request.getPerfil() != null && !"".equals(request.getPerfil())) {

			if (request.getPerfil().equals("EMPRESA_USER")) {

				empresa.getUsuario().getRoles().add(Usuario.ROLE_EMPRESA_USER);

			} else if (request.getPerfil().equals("EMPRESA_ADMIN")) {

				empresa.getUsuario().getRoles().add(Usuario.ROLE_EMPRESA_ADMIN);
			}
		}

		Empresa empresaCriada = empresaService.save(empresa);
		return new ResponseEntity<Empresa>(empresaCriada, HttpStatus.CREATED);
	}


    @Operation(summary = "Serviço responsável por listar todos as empresas do sistema.")

    @GetMapping
    public List<Empresa> listarTodos() {
        return empresaService.listarTodos();
    }

    @Operation(summary = "Serviço responsável por listar uma empresa especifica do sistema.")

    @GetMapping("/{id}")
    public Empresa obterPorID(@PathVariable Long id) {
        return empresaService.obterPorID(id);
    }

    @Operation(summary = "Serviço responsável por alterar dados de uma empresa especifica do sistema.")

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> update(@PathVariable("id") Long id, @RequestBody EmpresaRequest request) {

        empresaService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Serviço responsável por excluir uma empresa especifica do sistema.")

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        empresaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/filtrar")
    public List<Empresa> filtrar(
            @RequestParam(value = "cnpj", required = false) String cnpj,
            @RequestParam(value = "site", required = false) String site) {
        return empresaService.filtrar(cnpj, site);
    }
}