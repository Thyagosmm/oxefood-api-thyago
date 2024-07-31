package br.com.ifpe.oxefoodapithyago.modelo.cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;

    @Transactional
public Cliente save(Cliente cliente) {
    cliente.setHabilitado(Boolean.TRUE);
    cliente.setVersao(1L);
    cliente.setDataCriacao(LocalDate.now());

    // Salvar cliente primeiro
    Cliente clienteSalvo = repository.save(cliente);

    // Salvar endereços associados
    List<EnderecoCliente> enderecos = cliente.getEnderecos();
    if (enderecos != null && !enderecos.isEmpty()) {
        for (EnderecoCliente endereco : enderecos) {
            endereco.setCliente(clienteSalvo);
            endereco.setHabilitado(Boolean.TRUE);
            enderecoClienteRepository.save(endereco);
        }
    }

    return clienteSalvo;
}


    @Transactional
    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public Cliente obterPorID(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @Transactional
public void update(Long id, Cliente clienteAlterado) {
    Cliente cliente = obterPorID(id);
    cliente.setNome(clienteAlterado.getNome());
    cliente.setDataNascimento(clienteAlterado.getDataNascimento());
    cliente.setCpf(clienteAlterado.getCpf());
    cliente.setFoneCelular(clienteAlterado.getFoneCelular());
    cliente.setFoneFixo(clienteAlterado.getFoneFixo());
    
    // Atualizar endereços
    List<EnderecoCliente> enderecosAtualizados = clienteAlterado.getEnderecos();
    if (enderecosAtualizados != null) {
        for (EnderecoCliente enderecoAtualizado : enderecosAtualizados) {
            if (enderecoAtualizado.getId() != null) {
                // Atualizar endereço existente
                EnderecoCliente enderecoExistente = enderecoClienteRepository.findById(enderecoAtualizado.getId()).orElse(null);
                if (enderecoExistente != null) {
                    enderecoExistente.setRua(enderecoAtualizado.getRua());
                    enderecoExistente.setNumero(enderecoAtualizado.getNumero());
                    enderecoExistente.setBairro(enderecoAtualizado.getBairro());
                    enderecoExistente.setCep(enderecoAtualizado.getCep());
                    enderecoExistente.setCidade(enderecoAtualizado.getCidade());
                    enderecoExistente.setEstado(enderecoAtualizado.getEstado());
                    enderecoExistente.setComplemento(enderecoAtualizado.getComplemento());
                    enderecoClienteRepository.save(enderecoExistente);
                }
            } else {
                // Adicionar novo endereço
                enderecoAtualizado.setCliente(cliente);
                enderecoClienteRepository.save(enderecoAtualizado);
            }
        }
        
        // Remover endereços que não estão mais presentes
        List<EnderecoCliente> enderecosExistentes = cliente.getEnderecos();
        if (enderecosExistentes != null) {
            for (EnderecoCliente enderecoExistente : enderecosExistentes) {
                if (!enderecosAtualizados.contains(enderecoExistente)) {
                    enderecoClienteRepository.delete(enderecoExistente);
                }
            }
        }
    }

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
        listaEnderecoCliente = new ArrayList<>();
    }
    
    listaEnderecoCliente.add(endereco);
    cliente.setEnderecos(listaEnderecoCliente);
    cliente.setVersao(cliente.getVersao() + 1);
    repository.save(cliente);
   
    return endereco;
}

 
    @Transactional
   public EnderecoCliente atualizarEnderecoCliente(Long id, EnderecoCliente enderecoAlterado) {

       EnderecoCliente endereco = enderecoClienteRepository.findById(id).get();
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
    EnderecoCliente endereco = enderecoClienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    endereco.setHabilitado(Boolean.FALSE);
    enderecoClienteRepository.save(endereco);

    Cliente cliente = this.obterPorID(endereco.getCliente().getId());
    cliente.getEnderecos().remove(endereco);
    cliente.setVersao(cliente.getVersao() + 1);
    repository.save(cliente);
}
}