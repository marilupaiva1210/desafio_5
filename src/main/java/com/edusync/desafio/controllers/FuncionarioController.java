package com.edusync.desafio.controllers;

import com.edusync.desafio.models.Funcionario;
import com.edusync.desafio.services.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastra dos funcionários", description = "Faz o cadastro dos funcionários")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity cadastrarFuncionario(@RequestBody Funcionario funcionario) {

        try {
            funcionarioService.cadastrar(funcionario);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível cadastrar o funcionário.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Funcionário cadastrado!", HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Lista os funcionários", description = "Faz a listagem de todos os funcionários")
    @ApiResponse(responseCode = "200", description = "Sucesso!")


    public ResponseEntity listarFuncionarios() {

        try {
            return new ResponseEntity(funcionarioService.listar(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listar/{codigo}")
    @Operation(summary = "Lista funcionários por código", description = "Faz a listagem do funcionário referente ao código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo) {

        try {
            return new ResponseEntity(funcionarioService.pesquisar(codigo), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/alterar/{codigo}")
    @Operation(summary = "Altera os funcionários", description = "Faz a alteração dos funcionários baseado em código informado, com alterações atualizadas no body")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity alterar(@PathVariable Integer codigo, @RequestBody Funcionario funcionario) {

        try {
            funcionarioService.update(codigo, funcionario);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível alterar", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(funcionario, HttpStatus.OK);
    }

    @DeleteMapping(value = "/tarefa/{codigo}")
    @Operation(summary = "Deleta os funcionários", description = "Faz a exclusão do funcionário escolhido pelo código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity deletar(@PathVariable Integer codigo) {

        try {
            funcionarioService.remover(codigo);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Código inválido!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("funcionário do código " + codigo + " foi removido! ",HttpStatus.OK);
    }

    @GetMapping(value = "/nome")
    @Operation(summary = "Listar por nome", description = "Faz a listagem conforme o nome do funcionário é informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarNome(String nome) {

        return new ResponseEntity(funcionarioService.listarPorNome(nome), HttpStatus.OK);

    }
}
