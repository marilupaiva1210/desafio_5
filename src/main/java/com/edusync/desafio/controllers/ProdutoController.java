package com.edusync.desafio.controllers;

import com.edusync.desafio.models.Produto;
import com.edusync.desafio.repositories.criteria.params.ProdutoFilterParam;
import com.edusync.desafio.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping()
    @Operation(summary = "Cadastra dos produtos", description = "Faz o cadastro dos produtos")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity cadastrarProduto(@RequestBody Produto produto) {

        try {
            produtoService.cadastrar(produto);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível cadastrar o produto.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Produto cadastrado!", HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Lista os produtos", description = "Faz a listagem de todos os produtos")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarProdutos() {

        try {
            return new ResponseEntity<>(produtoService.listar(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{codigo}")
    @Operation(summary = "Lista os produtos positivos", description = "Faz a listagem de todos os produtos que são positivos")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarPositivo() {

        try {
            return new ResponseEntity<>(produtoService.listarPositivo(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/consultar/{codigo}")
    @Operation(summary = "Lista os produtos por código", description = "Faz a listagem do produto referente ao código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity consultarProduto(@PathVariable Integer codigo) {

        try {
            return new ResponseEntity<>(produtoService.consultar(codigo), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping(value = "/alterar/{codigo}")
    @Operation(summary = "Altera os produtos", description = "Faz a alteração dos produtos baseado em código informado, com alterações atualizadas no body")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity atualizarProduto(@PathVariable Integer codigo, @RequestBody Produto produto) {

        try {
            produtoService.update(codigo, produto);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Erro! Tente novamente", HttpStatus.OK);
        }
        return new ResponseEntity(produto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{codigo}")
    @Operation(summary = "Deleta os produtos", description = "Faz a exclusão do produto escolhido pelo código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity apagarProduto(@PathVariable Integer codigo) {

        try {
            produtoService.remover(codigo);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Produto do código " + codigo + " foi removido! ",HttpStatus.OK);
    }

    @GetMapping(value = "/nome")
    @Operation(summary = "Lista por descrição", description = "Faz a listagem conforme a descrição do produto é informada")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarDescricao(@RequestParam(defaultValue = "") String descricao) {

        return new ResponseEntity(produtoService.listarPorDescricao(descricao), HttpStatus.OK);
    }
    @GetMapping(value = "/filtrar")
    @Operation(summary = "Filtra os produtos cadastrados", description = "Faz a filtragem do cadastro conforme os dados do produto são informados")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity filtrarCadastrados(@RequestParam(required = false) String descricao,
                                             @RequestParam(required = false) BigDecimal precoVenda,
                                             @RequestParam(required = false) Integer saldoAtual) {

        ProdutoFilterParam params = new ProdutoFilterParam();
        params.setDescricao(descricao);
        params.setPrecoVenda(precoVenda);
        params.setSaldoAtual(saldoAtual);

        return new ResponseEntity<>(produtoService.filtrar(params), HttpStatus.OK);

    }
}