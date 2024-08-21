package br.com.ifpe.oxefoodapithyago.modelo.empresa;

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
@Table(name = "Empresa")
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Empresa extends EntidadeAuditavel {

    @Column(nullable = false)
    private String site;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false)
    private String inscricaoEstadual;

    @Column(nullable = false)
    private String nomeEmpresarial;

    @Column(nullable = false)
    private String nomeFantasia;

    @Column(nullable = false)
    private String fone;

    @Column(nullable = false)
    private String foneAlternativo;

}