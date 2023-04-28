package com.edusync.desafio.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_funcionario_responsavel")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;

    @Column(length = 255)
    private String nome;
    @Column(length = 35)
    private String cargo;
    @Column(length = 50)
    private String cpf;

}
