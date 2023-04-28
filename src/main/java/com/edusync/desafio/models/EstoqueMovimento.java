package com.edusync.desafio.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "tb_estoque_movimento")
public class EstoqueMovimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;
    @Column(name = "data_hora")
    private Date dataHora;
    @Column(name = "tipo_movimento")
    private String tipoMovimento;
    @Column(length = 300)
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

}
