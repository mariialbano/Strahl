package br.com.thomasmariana.apireststhral.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message="{usuario.nome.notblank}")
    private String nome;

    @NotBlank(message="{usuario.email.notblank}")
    private String email;

    @NotBlank(message ="{usuario.tel.notblank}")
    private String tel;

    @NotBlank(message ="{usuario.estado.notblank}")
    private String estado;
}


