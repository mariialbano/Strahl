package br.com.thomasmariana.apireststhral.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.thomasmariana.apireststhral.model.Usuario;
import br.com.thomasmariana.apireststhral.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("usuario")
public class UsuarioController{
    @Autowired
    UsuarioRepository repository;

    @GetMapping
    public Page<Usuario> index (

    @PageableDefault(size = 3) Pageable pageable
    ){
        return repository.findAll(pageable);
    }


    @GetMapping("{id}")
    public ResponseEntity<Usuario> listarusuario(@PathVariable Long id){

        return repository
            .findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletarUsuario(@PathVariable Long id){

        verificarExistenciaUsuario(id);
        repository.deleteById(id);
    }


    @PutMapping("{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){

        verificarExistenciaUsuario(id);
        usuario.setId(id);
        return repository.save(usuario);
    }


    @PostMapping("{id}")
    @ResponseStatus(CREATED)
    public Usuario create(@RequestBody @Valid Usuario usuario){
        return repository.save(usuario);
    }

    private void verificarExistenciaUsuario(Long id){
        repository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Não existe usuário com o id informado."));
    }




}