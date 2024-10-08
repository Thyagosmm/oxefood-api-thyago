package br.com.ifpe.oxefoodapithyago.modelo.cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ifpe.oxefoodapithyago.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefoodapithyago.modelo.mensagens.EmailService;
import br.com.ifpe.oxefoodapithyago.util.exception.ClienteException;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailService emailService;

    @Transactional
    public Cliente save(Cliente cliente) {

        usuarioService.save(cliente.getUsuario());

        cliente.setHabilitado(Boolean.TRUE);
        cliente.setVersao(1L);
        cliente.setDataCriacao(LocalDate.now());
        Cliente clienteSalvo = repository.save(cliente);
        emailService.enviarEmailConfirmacaoCadastroCliente(clienteSalvo);
        return clienteSalvo;
    }

    @Transactional
    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public Cliente obterPorID(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ClienteException("Cliente", id));
    }

    @Transactional
    public void update(Long id, Cliente clienteAlterado) {
        Cliente cliente = obterPorID(id);
        cliente.setNome(clienteAlterado.getNome());
        cliente.setDataNascimento(clienteAlterado.getDataNascimento());
        cliente.setCpf(clienteAlterado.getCpf());
        cliente.setFoneCelular(clienteAlterado.getFoneCelular());
        cliente.setFoneFixo(clienteAlterado.getFoneFixo());
        cliente.setVersao(cliente.getVersao() + 1);
        repository.save(cliente);
    }

    @Transactional
    public void delete(Long id) {
        Cliente cliente = obterPorID(id);
        cliente.setHabilitado(Boolean.FALSE);
        cliente.setVersao(cliente.getVersao() + 1);
        repository.save(cliente);
    }

    @Transactional
    public EnderecoCliente adicionarEnderecoCliente(Long clienteId, EnderecoCliente endereco) {
        Cliente cliente = this.obterPorID(clienteId);

        endereco.setCliente(cliente);
        endereco.setHabilitado(Boolean.TRUE);
        enderecoClienteRepository.save(endereco);

        List<EnderecoCliente> listaEnderecoCliente = cliente.getEnderecos();

        if (listaEnderecoCliente == null) {
            listaEnderecoCliente = new ArrayList<EnderecoCliente>();
        }

        listaEnderecoCliente.add(endereco);
        cliente.setEnderecos(listaEnderecoCliente);
        cliente.setVersao(cliente.getVersao() + 1);
        repository.save(cliente);

        return endereco;
    }

    @Transactional
    public EnderecoCliente atualizarEnderecoCliente(Long id, EnderecoCliente enderecoAlterado) {
        EnderecoCliente endereco = enderecoClienteRepository.findById(id)
                .orElseThrow(() -> new ClienteException("Endereço do Cliente", id));
        endereco.setRua(enderecoAlterado.getRua());
        endereco.setNumero(enderecoAlterado.getNumero());
        endereco.setBairro(enderecoAlterado.getBairro());
        endereco.setCep(enderecoAlterado.getCep());
        endereco.setCidade(enderecoAlterado.getCidade());
        endereco.setEstado(enderecoAlterado.getEstado());
        endereco.setComplemento(enderecoAlterado.getComplemento());

        return enderecoClienteRepository.save(endereco);
    }

    @Transactional
    public void removerEnderecoCliente(Long id) {
        EnderecoCliente endereco = enderecoClienteRepository.findById(id)
                .orElseThrow(() -> new ClienteException("Endereço do Cliente", id));
        endereco.setHabilitado(Boolean.FALSE);
        enderecoClienteRepository.save(endereco);

        Cliente cliente = this.obterPorID(endereco.getCliente().getId());
        cliente.getEnderecos().remove(endereco);
        cliente.setVersao(cliente.getVersao() + 1);
        repository.save(cliente);
    }

    public List<Cliente> filtrar(String cpf, String nome) {
        List<Cliente> listaClientes = repository.findAll();

        if ((cpf != null && !"".equals(cpf)) &&
                (nome == null || "".equals(nome)))
            listaClientes = repository.consultarPorCpf(cpf);
        else if ((cpf == null || "".equals(cpf)) &&
                (nome != null && !"".equals(nome))) {
            listaClientes = repository.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome);
        }
        return listaClientes;
    }
}
