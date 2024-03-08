package br.com.ifpe.oxefoodapithyago.api.entregador;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefoodapithyago.modelo.entregador.Entregador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorRequest {

   @JsonFormat(pattern = "dd/MM/yyyy")
   private LocalDate dataNascimento;

   private String nome;
   private String cpf;
   private String rg;
   private String foneCelular;
   private String foneFixo;
   private int qtdEntregasRealizadas;
   private double valorPorFrete;
   private String rua;
   private String numero;
   private String bairro;
   private String cidade;
   private String cep;
   private String uf;
   private String complemento;
   private boolean ativo;

   public Entregador build() {
       return Entregador.builder()
           .nome(nome)
           .cpf(cpf)
           .rg(rg)
           .dataNascimento(dataNascimento)
           .foneCelular(foneCelular)
           .foneFixo(foneFixo)
           .qtdEntregasRealizadas(qtdEntregasRealizadas)
           .valorPorFrete(valorPorFrete)
           .rua(rua)
           .numero(numero)
           .bairro(bairro)
           .cidade(cidade)
           .cep(cep)
           .uf(uf)
           .complemento(complemento)
           .ativo(ativo)
           .build();
   }
}
