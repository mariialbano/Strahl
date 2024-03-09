package br.com.thomasmariana.apireststhral.model;

import java.util.Random;

public record Produto(Long id, String nome, double preco, double tela, int armazenamento,
        String cor, double camera) {

    public Produto(Long id, String nome, double preco, double tela, int armazenamento,
            String cor, double camera) {
                var key = (id== null) ? Math.abs(new Random().nextLong()):id;
                this.id = key;
                this.nome = nome;
                this.preco =  preco;
                this.tela = tela;
                this.armazenamento = armazenamento;
                this.cor = cor;
                this.camera = camera;

    }

}
