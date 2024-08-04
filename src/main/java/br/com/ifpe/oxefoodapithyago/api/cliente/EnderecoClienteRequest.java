package br.com.ifpe.oxefoodapithyago.api.cliente;

import br.com.ifpe.oxefoodapithyago.modelo.cliente.EnderecoCliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class EnderecoClienteRequest {

  @NotBlank(message = "A rua é de preenchimento obrigatório")
  private String rua;

  @NotBlank(message = "O número é de preenchimento obrigatório")
  private String numero;

  @NotBlank(message = "O bairro é de preenchimento obrigatório")
  private String bairro;

  @Pattern(regexp = "\\d{8}", message = "O CEP deve ter exatamente 8 dígitos")
  private String cep;

  @NotBlank(message = "A cidade é de preenchimento obrigatório")
  private String cidade;

  @NotBlank(message = "O estado é de preenchimento obrigatório")
  private String estado;

  private String complemento;

  public EnderecoCliente build() {

      return EnderecoCliente.builder()
              .rua(rua)
              .numero(numero)
              .bairro(bairro)
              .cep(cep)
              .cidade(cidade)
              .estado(estado)
              .complemento(complemento)
              .build();
  }
}
