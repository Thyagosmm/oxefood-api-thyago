package br.com.ifpe.oxefoodapithyago.modelo.entregador;

import java.time.LocalDate;

import org.hibernate.annotations.SQLRestriction;

import br.com.ifpe.oxefoodapithyago.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Entregador")
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Entregador extends EntidadeAuditavel {

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true, length = 9)
    private String rg;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(length = 11)
    private String foneCelular;

    @Column(length = 11)
    private String foneFixo;

    @Column
    private int qtdEntregasRealizadas;

    @Column
    private double valorPorFrete;

    @Column
    private String rua;

    @Column
    private String numero;

    @Column
    private String bairro;

    @Column
    private String cidade;

    @Column(length = 8)
    private String cep;

    @Column(length = 2)
    private String uf;

    @Column
    private String complemento;

    @Column
    private boolean ativo;

}