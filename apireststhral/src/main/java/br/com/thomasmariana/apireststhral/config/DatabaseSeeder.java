package br.com.thomasmariana.apireststhral.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.thomasmariana.apireststhral.model.Produto;
import br.com.thomasmariana.apireststhral.model.Usuario;
import br.com.thomasmariana.apireststhral.repository.ProdutoRepository;
import br.com.thomasmariana.apireststhral.repository.UsuarioRepository;


@Configuration
public class DatabaseSeeder implements CommandLineRunner{
    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        produtoRepository.saveAll(
            List.of(
                Produto.builder().id(1L).nome("Celular").preco("1988").tela(58).armazenamento(45).cor("Rosa").camera(17).build(),
                Produto.builder().id(2L).nome("hahah").preco("15677").tela(67).armazenamento(50).cor("Roxo").camera(15).build(),
                Produto.builder().id(3L).nome("Note").preco("15544").tela(67).armazenamento(78).cor("Amarelo").camera(45).build()
            )
        );

        usuarioRepository.saveAll(
            List.of(
                Usuario.builder()
                    .id(1L)
                    .nome("Cloud")
                    .email("Curso de AWS")
                    .tel("11977775555")
                    .estado("10 horas")
                    .produto(produtoRepository.findById(1L).get())
                    .build()
            )
        );



    }
}