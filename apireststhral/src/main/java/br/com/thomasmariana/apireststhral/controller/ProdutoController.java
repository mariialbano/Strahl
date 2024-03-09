package br.com.thomasmariana.apireststhral.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thomasmariana.apireststhral.model.Produto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    Logger log = LoggerFactory.getLogger(getClass());
    List<Produto> repository = new ArrayList<>();

    @GetMapping
    public List<Produto> index() {
        return repository;
    }

    // MÉTODO POST
    @PostMapping
    @RequestStatus(code = HttpStatus.CREATED)
    public Produto postProduto(@RequestBody Produto produto) {
        // TODO: process POST request
        log.info("Cadastrando produto {}", produto);
        repository.add(produto);
        return produto;
    }

    // MÉTODO GET
    @GetMapping("{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable Long id) {
        log.info("Buscando produto por id {}", id);

        var produtoEncontrado = repository.stream().filter((c) -> c.id().equals(id)).findFirst();

        if (produtoEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produtoEncontrado.get());

    }

    // MÉTODO DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProduto(@PathVariable Long id) {
        log.info("Apagando Produto");

        var produtoEncontrado = getProdutoById(id);

        if (produtoEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.remove(produtoEncontrado.get());
        return ResponseEntity.noContent().build();
    }

    private Optional<Produto> getProdutoById(Long id) {
        var produtoEncontrado = repository.stream()
                .filter(c -> c.id().equals(id))
                .findFirst();
        return produtoEncontrado;

    }

    // MÉTODO PUT
    @PutMapping("{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produto) {
        // TODO: process PUT request
        log.info("Atualizando produto com id {} para {}", id, produto);
        // Buscar o produto
        var produtoEncontrado = getProdutoById(id);

        if (produtoEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        // Criar um novo produto com os novos dados.

        var produtoAntigo = produtoEncontrado.get();
        var produtoNovo = new Produto(id, 
                produto.nome(),
                produto.preco(),
                produto.tela(),
                produto.armazenamento(),
                produto.cor(),
                produto.camera());
        //apagar o produto antigo

        repository.remove(produtoAntigo);

        //adicionar produto novo7

        repository.add(produtoNovo);
        return ResponseEntity.ok(produtoNovo);
    }

}
