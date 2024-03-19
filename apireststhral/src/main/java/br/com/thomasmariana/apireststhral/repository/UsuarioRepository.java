package br.com.thomasmariana.apireststhral.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.thomasmariana.apireststhral.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {    
    
}