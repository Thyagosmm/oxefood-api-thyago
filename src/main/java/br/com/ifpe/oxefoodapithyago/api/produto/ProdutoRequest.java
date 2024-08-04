package br.com.ifpe.oxefoodapithyago.api.produto;

import br.com.ifpe.oxefoodapithyago.modelo.produto.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "O título é de preenchimento obrigatório")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
    private String titulo;

    @NotBlank(message = "O código é de preenchimento obrigatório")
    @Size(max = 50, message = "O código deve ter no máximo 50 caracteres")
    private String codigo;

    @Size(max = 100, message = "A descrição deve ter no máximo 100 caracteres")
    private String descricao;

    @NotNull(message = "O valor unitário é de preenchimento obrigatório")
    @Positive(message = "O valor unitário deve ser positivo")
    private Double valorUnitario;

    @NotNull(message = "O tempo de entrega mínimo é de preenchimento obrigatório")
    @Positive(message = "O tempo de entrega mínimo deve ser positivo")
    private Integer tempoDeEntregaMinimoEmMinutos;

    @NotNull(message = "O tempo de entrega máximo é de preenchimento obrigatório")
    @Positive(message = "O tempo de entrega máximo deve ser positivo")
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
