package br.com.ifpe.oxefoodapithyago.modelo.cliente;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.FetchMode;
import br.com.ifpe.oxefoodapithyago.modelo.acesso.Usuario;
import br.com.ifpe.oxefoodapithyago.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

public class Cliente extends EntidadeAuditavel  {
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;


    @OneToMany(mappedBy = "cliente", orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<EnderecoCliente> enderecos;

    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(nullable = false)
    private LocalDate dataNascimento;
    
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(length = 11)
    private String foneCelular;

    @Column(length = 11)
    private String foneFixo;

    public Cliente orElseThrow(Object object) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }

}
