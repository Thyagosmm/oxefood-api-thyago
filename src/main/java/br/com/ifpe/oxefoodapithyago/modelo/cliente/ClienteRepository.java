package br.com.ifpe.oxefoodapithyago.modelo.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //Exemplo de uma busca exata pelo cpf
   @Query(value = "SELECT c FROM Cliente c WHERE c.cpf = :cpf")
   List<Cliente> consultarPorCpf(String cpf);

   //Exemplo de uma busca aproximada com ordenação pelo nome:
   // @Query(value = "SELECT c FROM Cliente c WHERE c.nome like %:nome% ORDER BY c.nome")
   // List<Produto> consultarPorNome(String titulo);
   List<Cliente> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);

}