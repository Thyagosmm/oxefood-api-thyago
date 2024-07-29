package br.com.ifpe.oxefoodapithyago.api.categoriaproduto;

import br.com.ifpe.oxefoodapithyago.modelo.categoriaproduto.CategoriaProduto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CategoriaProdutoRequest {

    private String descricaoCategoria;

    public CategoriaProduto build() {

        return CategoriaProduto.builder()
                .descricaoCategoria(descricaoCategoria)
                .build();
    }

}
