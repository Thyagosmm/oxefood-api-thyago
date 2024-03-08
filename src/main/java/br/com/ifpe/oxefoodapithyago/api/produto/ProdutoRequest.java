package br.com.ifpe.oxefoodapithyago.api.produto;

import br.com.ifpe.oxefoodapithyago.modelo.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProdutoRequest {

   private String titulo;
   private String codigoDoProduto;
   private String descricao;
   private double valorUnitario;
   private int tempoDeEntregaMinimoEmMinutos;
   private int tempoDeEntregaMaximoEmMinutos;

   public Produto build() {

       return Produto.builder()
           .titulo(titulo)
           .codigoDoProduto(codigoDoProduto)
           .descricao(descricao)
           .valorUnitario(valorUnitario)
           .tempoDeEntregaMinimoEmMinutos(tempoDeEntregaMinimoEmMinutos)
           .tempoDeEntregaMaximoEmMinutos(tempoDeEntregaMaximoEmMinutos)
           .build();
   }

}
