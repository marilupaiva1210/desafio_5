package com.edusync.desafio.services;

import com.edusync.desafio.models.Funcionario;
import com.edusync.desafio.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void cadastrar(Funcionario funcionario) {

        funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listar() {

        return funcionarioRepository.findAll();
    }

    public void update(Integer codigo, Funcionario funcionario) {
        if (funcionarioRepository.existsById(codigo)) {
            funcionarioRepository.save(funcionario);
        }
    }

    public Funcionario pesquisar(Integer codigo) {

        Optional<Funcionario> optFuncionario = funcionarioRepository.findById(codigo);
        if (optFuncionario.isEmpty()) {

            return null;
        }
        return optFuncionario.get();
    }

    public void remover(Integer codigo) {

        if (funcionarioRepository.existsById(codigo)){
            funcionarioRepository.deleteById(codigo);
        }
    }

    public List<Funcionario> listarPorNome(String nome) {

        return funcionarioRepository.findByNomeContainingIgnoreCase(nome);
    }
}

