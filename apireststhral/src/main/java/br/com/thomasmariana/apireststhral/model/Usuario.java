package br.com.thomasmariana.apireststhral.model;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne
    private Produto produto;
}


