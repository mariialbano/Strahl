package br.com.thomasmariana.apireststhral.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.thomasmariana.apireststhral.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {    
    
}