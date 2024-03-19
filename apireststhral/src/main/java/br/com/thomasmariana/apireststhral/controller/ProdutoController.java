package br.com.thomasmariana.apireststhral.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.thomasmariana.apireststhral.model.Produto;
import br.com.thomasmariana.apireststhral.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/produto")
@Slf4j
public class ProdutoController {
    
    @Autowired
    ProdutoRepository repository;

    @GetMapping
    public List<Produto> index() {
        return repository.findAll();
    }

    // MÉTODO POST
    @PostMapping
    @RequestStatus(code = HttpStatus.CREATED)
    public Produto postProduto(@RequestBody Produto produto) {
        
        log.info("Cadastrando produto {}", produto);
        return repository.save(produto);
    }

    // MÉTODO GET
    @GetMapping("{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable Long id) {
        log.info("Buscando produto por id {}", id);

        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    // MÉTODO DELETE
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteProduto(@PathVariable Long id) {
        log.info("Apagando Produto");

        verificarSeExisteProduto(id);
        repository.deleteById(id);
    }

    private void verificarSeExisteProduto(Long id) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Não existe produto com o id informado. Consulte lista em /produto"
        ));
    }

    // MÉTODO PUT
    @PutMapping("{id}")
    public Produto updateProduto(@PathVariable Long id, @RequestBody Produto produto) {
        
        log.info("Atualizando produto com id {} para {}", id, produto);
        // Buscar o produto
        
        verificarSeExisteProduto(id);
        produto.setId(id);
        return repository.save(produto);
    }

}
