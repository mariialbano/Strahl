package br.com.thomasmariana.apireststhral.model;

import jakarta.persistence.GeneratedValue;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message ="Informe um nome")
    @Size(min= 3, max = 255, message = "Nome inválido")
    private String nome;

    @Positive(message = "{produto.preco.positive}")
    private String preco;

    @Positive(message = "{produto.tela.positive}")
    private double tela;

    @Positive(message = "{produto.armazenamento.positive}")
    private int armazenamento;

    private String cor;

    @Positive(message = "{produto.camera.positive}")
    private double camera;
}
