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

    private Long idCategoria;
    private String titulo;
    private String codigo;
    private String descricao;
    private Double valorUnitario;
    private Integer tempoDeEntregaMinimoEmMinutos;
    private Integer tempoDeEntregaMaximoEmMinutos;

    public Produto build() {
        return Produto.builder()
                .titulo(titulo)
                .codigo(codigo)
                .descricao(descricao)
                .valorUnitario(valorUnitario)
                .tempoDeEntregaMinimoEmMinutos(tempoDeEntregaMinimoEmMinutos)
                .tempoDeEntregaMaximoEmMinutos(tempoDeEntregaMaximoEmMinutos)
                .build();
    }
}
