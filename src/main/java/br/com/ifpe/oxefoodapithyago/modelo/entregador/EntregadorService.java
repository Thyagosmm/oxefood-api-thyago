package br.com.ifpe.oxefoodapithyago.modelo.entregador;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class EntregadorService {

   @Autowired
   private EntregadorRepository repository;

   @Transactional
   public Entregador save(Entregador entregador) {

       entregador.setHabilitado(Boolean.TRUE);
       entregador.setVersao(1L);
       entregador.setDataCriacao(LocalDate.now());
       return repository.save(entregador);
   }

}
