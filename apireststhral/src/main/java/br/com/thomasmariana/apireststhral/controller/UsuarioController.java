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
import br.com.thomasmariana.apireststhral.model.Usuario;
import br.com.thomasmariana.apireststhral.repository.ProdutoRepository;
import br.com.thomasmariana.apireststhral.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {
    
    @Autowired
    UsuarioRepository repository;

    @GetMapping
    public List<Usuario> index() {
        return repository.findAll();
    }

    // MÉTODO POST
    @PostMapping
    @RequestStatus(code = HttpStatus.CREATED)
    public Usuario postUsuario(@RequestBody Usuario usuario) {
        
        log.info("Cadastrando usuário {}", usuario);
        return repository.save(usuario);
    }

    // MÉTODO GET
    @GetMapping("{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
        log.info("Buscando usuário por id {}", id);

        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    // MÉTODO DELETE
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUsuario(@PathVariable Long id) {
        log.info("Apagando Usuario");

        verificarSeExisteUsuario(id);
        repository.deleteById(id);
    }

    private void verificarSeExisteUsuario(Long id) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Não existe usuário com o id informado. Consulte lista em /usuario"
        ));
    }

    // MÉTODO PUT
    @PutMapping("{id}")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        
        log.info("Atualizando usuario com id {} para {}", id, usuario);
        // Buscar o produto
        
        verificarSeExisteUsuario(id);
        usuario.setId(id);
        return repository.save(usuario);
    }

}
