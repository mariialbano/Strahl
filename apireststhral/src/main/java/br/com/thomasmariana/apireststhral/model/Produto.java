package br.com.thomasmariana.apireststhral.model;

import jakarta.persistence.GeneratedValue;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private BigDecimal preco;
    private double tela;
    private int armazenamento;
    private String cor;
    private double camera;
}
