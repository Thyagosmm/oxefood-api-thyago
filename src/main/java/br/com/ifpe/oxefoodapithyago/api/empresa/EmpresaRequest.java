package br.com.ifpe.oxefoodapithyago.api.empresa;

import br.com.ifpe.oxefoodapithyago.modelo.empresa.Empresa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class EmpresaRequest {

    @NotBlank(message = "O site é de preenchimento obrigatório")
    @Size(max = 100, message = "O site deve ter no máximo 100 caracteres")
    private String site;

    @NotBlank(message = "O CNPJ é de preenchimento obrigatório")
    @Pattern(regexp = "\\d{14}", message = "O CNPJ deve ter exatamente 14 dígitos")
    private String cnpj;

    @NotBlank(message = "A inscrição estadual é de preenchimento obrigatório")
    @Pattern(regexp = "\\d{12}", message = "A inscrição estadual deve ter exatamente 9 dígitos")
    private String inscricaoEstadual;

    @NotBlank(message = "O nome empresarial é de preenchimento obrigatório")
    @Size(max = 100, message = "O nome empresarial deve ter no máximo 100 caracteres")
    private String nomeEmpresarial;

    @NotBlank(message = "O nome fantasia é de preenchimento obrigatório")
    @Size(max = 100, message = "O nome fantasia deve ter no máximo 100 caracteres")
    private String nomeFantasia;

    @Pattern(regexp = "^81\\d{9}$", message = "O telefone deve começar com 81 e ter 11 dígitos")
    private String fone;

    @Pattern(regexp = "^81\\d{9}$", message = "O telefone alternativo deve começar com 81 e ter 11 dígitos")
    private String foneAlternativo;

    public Empresa build() {

        return Empresa.builder()
                .site(site)
                .cnpj(cnpj)
                .inscricaoEstadual(inscricaoEstadual)
                .nomeEmpresarial(nomeEmpresarial)
                .nomeFantasia(nomeFantasia)
                .fone(fone)
                .foneAlternativo(foneAlternativo)
                .build();
    }
}