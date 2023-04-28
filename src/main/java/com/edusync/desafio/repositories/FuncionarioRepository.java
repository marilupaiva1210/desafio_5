package com.edusync.desafio.repositories;

import com.edusync.desafio.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    public List<Funcionario> findByNomeContainingIgnoreCase(String nome);
    public Funcionario findByNomeAndCargo(String nome, String cargo);

}

