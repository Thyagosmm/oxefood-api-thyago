package br.com.ifpe.oxefoodapithyago.modelo.produto;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Transactional
    public Produto save(Produto produto) {
        produto.setHabilitado(Boolean.TRUE);
        produto.setVersao(1L);
        produto.setDataCriacao(LocalDate.now());
        return repository.save(produto);
    }
}
