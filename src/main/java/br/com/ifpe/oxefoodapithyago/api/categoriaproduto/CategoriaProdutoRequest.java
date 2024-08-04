package br.com.ifpe.oxefoodapithyago.api.categoriaproduto;

import br.com.ifpe.oxefoodapithyago.modelo.categoriaproduto.CategoriaProduto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CategoriaProdutoRequest {

    @NotBlank(message = "A descrição é de preenchimento obrigatório")
    @Size(max = 100, message = "A descrição deve ter no máximo 100 caracteres")
    private String descricaoCategoria;

    public CategoriaProduto build() {

        return CategoriaProduto.builder()
                .descricaoCategoria(descricaoCategoria)
                .build();
    }

}
