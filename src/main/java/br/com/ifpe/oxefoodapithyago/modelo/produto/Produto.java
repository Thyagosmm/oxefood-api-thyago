package br.com.ifpe.oxefoodapithyago.modelo.produto;

import org.hibernate.annotations.SQLRestriction;

import br.com.ifpe.oxefoodapithyago.modelo.categoriaproduto.CategoriaProduto;
import br.com.ifpe.oxefoodapithyago.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Produto")
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Produto extends EntidadeAuditavel {

    @ManyToOne
    private CategoriaProduto categoria;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 50)
    private String codigo;

    @Column(length = 100)
    private String descricao;

    @Column(nullable = false)
    private double valorUnitario;

    @Column(nullable = false)
    private int tempoDeEntregaMinimoEmMinutos;

    @Column(nullable = false)
    private int tempoDeEntregaMaximoEmMinutos;
}
