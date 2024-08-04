package br.com.ifpe.oxefoodapithyago.api.entregador;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefoodapithyago.modelo.entregador.Entregador;
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
public class EntregadorRequest {

   @JsonFormat(pattern = "dd/MM/yyyy")
   @NotBlank(message = "A data de nascimento é de preenchimento obrigatório")
   @Past(message = "A data de nascimento deve ser válida")
   private LocalDate dataNascimento;

   @NotBlank(message = "O nome é de preenchimento obrigatório")
   @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
   private String nome;
   
   @NotBlank(message = "O CPF é de preenchimento obrigatório")
   @Pattern(regexp = "\\d{11}", message = "O CPF deve ter exatamente 11 dígitos")
   private String cpf;
   
   @NotBlank(message = "O RG é de preenchimento obrigatório")
   @Pattern(regexp = "\\d{9}", message = "O RG deve ter exatamente 9 dígitos")
   private String rg;
   
   @Pattern(regexp = "^81\\d{9}$", message = "O telefone celular deve começar com 81 e ter 11 dígitos")
   private String foneCelular;
   
   @Pattern(regexp = "^81\\d{9}$", message = "O telefone fixo deve começar com 81 e ter 11 dígitos")
   private String foneFixo;

   private int qtdEntregasRealizadas;

   private double valorPorFrete;

   @NotBlank(message = "A rua é de preenchimento obrigatório")
   private String rua;

   @NotBlank(message = "O número é de preenchimento obrigatório")
   private String numero;

   @NotBlank(message = "O bairro é de preenchimento obrigatório")
   private String bairro;

   @NotBlank(message = "A cidade é de preenchimento obrigatório")
   private String cidade;

   @Pattern(regexp = "\\d{8}", message = "O CEP deve ter exatamente 8 dígitos")
   private String cep;

   @NotBlank(message = "O UF é de preenchimento obrigatório")
   @Size(min = 2, max = 2, message = "O UF deve ter exatamente 2 caracteres")
   @Pattern(regexp = "^(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)$", message = "O estado deve ser uma sigla válida")
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
