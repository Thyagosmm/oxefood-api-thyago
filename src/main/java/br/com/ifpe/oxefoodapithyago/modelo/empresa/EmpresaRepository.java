package br.com.ifpe.oxefoodapithyago.modelo.empresa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    //Exemplo de uma busca exata pelo cnpj
   @Query(value = "SELECT e FROM Empresa e WHERE e.cnpj = :cnpj")
   List<Empresa> consultarPorCnpj(String cnpj);

   //Exemplo de uma busca aproximada com ordenação pelo site:
    @Query(value = "SELECT e FROM Empresa e WHERE e.site like :site% ORDER BY e.site")
    List<Empresa> consultarPorSite(String site);
   //List<Empresa> findBySiteContainingIgnoreCaseOrderBySiteAsc(String site);

}