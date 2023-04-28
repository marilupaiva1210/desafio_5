package com.edusync.desafio.controllers;

import com.edusync.desafio.models.Funcionario;
import com.edusync.desafio.models.EstoqueMovimento;
import com.edusync.desafio.models.Produto;
import com.edusync.desafio.services.FuncionarioService;
import com.edusync.desafio.services.EstoqueMovimentoService;
import com.edusync.desafio.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/movimentos")
public class EstoqueMovimentoController {

    @Autowired
    private EstoqueMovimentoService estoqueMovimentoService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastra dos movimentos", description = "Faz o cadastro dos movimentos")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity cadastrar(@RequestBody EstoqueMovimento movimento, @RequestParam Integer codigoProduto, @RequestParam Integer codigoFuncionario) {

        Produto produto = produtoService.consultar(codigoProduto);
        movimento.setProduto(produto);

        Funcionario funcionario = funcionarioService.pesquisar(codigoFuncionario);
        movimento.setFuncionario(funcionario);

        try {
            estoqueMovimentoService.cadastrar(movimento);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível cadastrar o movimento.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Movimento cadastrado!", HttpStatus.CREATED);
    }

    @GetMapping()
    @Operation(summary = "Lista movimentos", description = "Faz a listagem de todos os movimentos")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listar() {

        try {
            return new ResponseEntity(estoqueMovimentoService.listarMovimento(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "{codigo}")
    @Operation(summary = "Lista movimentos por código", description = "Faz a listagem do movimento referente ao código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo) {

        try {
            return new ResponseEntity(estoqueMovimentoService.pesquisar(codigo), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/alterar/{codigo}")
    @Operation(summary = "Altera os movimentos", description = "Faz a alteração dos movimentos baseado em código informado, com alterações atualizadas no body")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity alterar(@PathVariable Integer codigo, @RequestBody EstoqueMovimento movimento) {

        try {
            estoqueMovimentoService.update(codigo, movimento);
        } catch (NoSuchElementException e) {

            return new ResponseEntity("Não foi possível alterar", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(movimento, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar/{codigo}")
    @Operation(summary = "Deleta os movimentos", description = "Faz a exclusão do movimento escolhido pelo código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity deletar(@PathVariable Integer codigo) {

        try {
            estoqueMovimentoService.remover(codigo);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Código inválido!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("movimento do código " + codigo + " foi removido! ",HttpStatus.OK);
    }

    @GetMapping(value = "/historico")
    @Operation(summary = "Lista o histórico do produto", description = "Faz a listagem do histórico do movimento do produto referente ao código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarHistoricoMovimento(Integer produtoId) {

        return new ResponseEntity<>(estoqueMovimentoService.listarMovimentoPorHistorico(produtoId), HttpStatus.OK);

    }
}
