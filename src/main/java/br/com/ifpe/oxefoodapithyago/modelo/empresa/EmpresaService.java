package br.com.ifpe.oxefoodapithyago.modelo.empresa;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefoodapithyago.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefoodapithyago.util.exception.EmpresaException;
import jakarta.transaction.Transactional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository repository;

    @Autowired
    private UsuarioService usuarioService;


    @Transactional
    public Empresa save(Empresa empresa) {

        usuarioService.save(empresa.getUsuario());

        empresa.setHabilitado(Boolean.TRUE);
        empresa.setVersao(1L);
        empresa.setDataCriacao(LocalDate.now());
        return repository.save(empresa);
    }

    @Transactional
    public List<Empresa> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public Empresa obterPorID(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmpresaException("Empresa", id));
    }

    @Transactional
    public void update(Long id, Empresa empresaAlterada) {
        Empresa empresa = obterPorID(id);
        empresa.setSite(empresaAlterada.getSite());
        empresa.setCnpj(empresaAlterada.getCnpj());
        empresa.setInscricaoEstadual(empresaAlterada.getInscricaoEstadual());
        empresa.setNomeEmpresarial(empresaAlterada.getNomeEmpresarial());
        empresa.setNomeFantasia(empresaAlterada.getNomeFantasia());
        empresa.setFone(empresaAlterada.getFone());
        empresa.setFoneAlternativo(empresaAlterada.getFoneAlternativo());
        empresa.setVersao(empresa.getVersao() + 1);
        repository.save(empresa);
    }

    @Transactional
    public void delete(Long id) {
        Empresa empresa = obterPorID(id);
        empresa.setHabilitado(Boolean.FALSE);
        empresa.setVersao(empresa.getVersao() + 1);
        repository.save(empresa);
    }

    public List<Empresa> filtrar(String cnpj, String site) {
        List<Empresa> listaEmpresas = repository.findAll();

        if ((cnpj != null && !"".equals(cnpj)) &&
                (site == null || "".equals(site)))
            listaEmpresas = repository.consultarPorCnpj(cnpj);
        else if ((cnpj == null || "".equals(cnpj)) &&
                (site != null && !"".equals(site))) {
            listaEmpresas = repository.consultarPorSite(site);
        }
        return listaEmpresas;
    }
}
