package br.com.ifpe.oxefoodapithyago.api.cliente;

import java.time.LocalDate;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefoodapithyago.modelo.acesso.Usuario;
import br.com.ifpe.oxefoodapithyago.modelo.cliente.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
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

public class ClienteRequest {

    @NotBlank(message = "O Nome é de preenchimento obrigatório")
    @Size(max = 100, message = "O Nome deve ter no máximo 100 caracteres")
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotBlank(message = "A data de nascimento é de preenchimento obrigatório")
    @Past(message = "A data de nascimento deve ser válida")
    private LocalDate dataNascimento;

    @NotBlank(message = "O CPF é de preenchimento obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve ter exatamente 11 dígitos")
    private String cpf;

    @Pattern(regexp = "^81\\d{9}$", message = "O telefone celular deve começar com 81 e ter 11 dígitos")
    private String foneCelular;

    @Pattern(regexp = "^81\\d{9}$", message = "O telefone fixo deve começar com 81 e ter 11 dígitos")
    private String foneFixo;

    @NotBlank(message = "O e-mail é de preenchimento obrigatório")
    private String email;

    @NotBlank(message = "A senha é de preenchimento obrigatório")
    private String password;

    public Usuario buildUsuario() {
        return Usuario.builder()
                .username(email)
                .password(password)
                .roles(Arrays.asList(Usuario.ROLE_CLIENTE))
                .build();
    }

    public Cliente build() {

        return Cliente.builder()
                .usuario(buildUsuario())
                .nome(nome)
                .dataNascimento(dataNascimento)
                .cpf(cpf)
                .foneCelular(foneCelular)
                .foneFixo(foneFixo)
                .build();
    }

}
