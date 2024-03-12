package br.com.thomasmariana.apireststhral.model;

import java.util.Random;

public record Usuario(Long id, String nome, String email, int tel, String estado) {

    public Usuario(Long id, String nome, String email, int tel, String estado) {
                var key = (id== null) ? Math.abs(new Random().nextLong()):id;
                this.id = key;
                this.nome = nome;
                this.email = email;
                this.tel = tel;
                this.estado = estado;

    }

}
