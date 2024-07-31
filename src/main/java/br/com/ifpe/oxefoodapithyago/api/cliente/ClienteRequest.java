package br.com.ifpe.oxefoodapithyago.api.cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import br.com.ifpe.oxefoodapithyago.modelo.cliente.Cliente;
import br.com.ifpe.oxefoodapithyago.modelo.cliente.EnderecoCliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ClienteRequest {

    private String nome;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private String cpf;

    private String foneCelular;

    private String foneFixo;

    private List<EnderecoClienteRequest> enderecos; // Adicionando a lista de endereços

    public Cliente build() {
        List<EnderecoCliente> enderecosList = enderecos != null ? enderecos.stream()
            .map(EnderecoClienteRequest::build)
            .collect(Collectors.toList()) : null;

        return Cliente.builder()
            .nome(nome)
            .dataNascimento(dataNascimento)
            .cpf(cpf)
            .foneCelular(foneCelular)
            .foneFixo(foneFixo)
            .enderecos(enderecosList) // Adicionando endereços ao cliente
            .build();
    }
}