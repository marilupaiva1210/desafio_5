package com.edusync.desafio.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;
    @Column(length = 255)
    private String descricao;

    @Column(name = "preco_venda")
    private BigDecimal precoVenda;

    @Column(name = "saldo_atual")
    private Integer saldoAtual;

}
