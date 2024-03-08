package br.com.ifpe.oxefoodapithyago.modelo.produto;

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
@Table(name = "Cliente")
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Produto extends EntidadeAuditavel{

    @Column(length = 100)
    private String titulo;

    @Column(name = "codigo_do_produto")
    private String codigoDoProduto;

    @Column(length = 500)
    private String descricao;

    @Column
    private double valorUnitario;

    @Column
    private int tempoDeEntregaMinimoEmMinutos;

    @Column
    private int tempoDeEntregaMaximoEmMinutos;
}
