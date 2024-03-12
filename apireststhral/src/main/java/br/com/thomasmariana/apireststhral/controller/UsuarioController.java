package br.com.thomasmariana.apireststhral.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.thomasmariana.apireststhral.model.Usuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    Logger log = LoggerFactory.getLogger(getClass());
    List<Usuario> repository = new ArrayList<>();

    @GetMapping
    public List<Usuario> index(){
        return repository;
    }

    // MÉTODO POST
    @PostMapping
    @RequestStatus(code = HttpStatus.CREATED)
    public Usuario postUsuario(@RequestBody Usuario usuario) {
        
        log.info("Cadastrando usuário {}", usuario);
        repository.add(usuario);
        return usuario;
    }

    // MÉTODO GET
    @GetMapping("{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
        log.info("Buscando usuário por id {}", id);

        var usuarioEncontrado = repository.stream().filter((c) -> c.id().equals(id)).findFirst();

        if (usuarioEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuarioEncontrado.get());

    }

    // MÉTODO DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> deleteProduto(@PathVariable Long id) {
        log.info("Apagando Produto");

        var usuarioEncontrado = getUsuarioById(id);

        if (usuarioEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.remove(usuarioEncontrado.get());
        return ResponseEntity.noContent().build();
    }

    private Optional<Usuario> getUsuarioById(Long id) {
        var usuarioEncontrado = repository.stream()
                .filter(c -> c.id().equals(id))
                .findFirst();
        return usuarioEncontrado;

    }

    // MÉTODO PUT
    @PutMapping("{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        
        log.info("Atualizando usuário com id {} para {}", id, usuario);

        // Buscar usuário
        var usuarioEncontrado = getUsuarioById(id);

        if (usuarioEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Criar um novo usuário com os novos dados.

        var usuarioAntigo = usuarioEncontrado.get();
        var usuarioNovo = new Usuario(id, 
                usuario.nome(),
                usuario.email(),
                usuario.tel(),
                usuario.estado());

        //apagar o usuário antigo

        repository.remove(usuarioAntigo);

        //adicionar usuário novo

        repository.add(usuarioNovo);
        return ResponseEntity.ok(usuarioNovo);
    }
}


