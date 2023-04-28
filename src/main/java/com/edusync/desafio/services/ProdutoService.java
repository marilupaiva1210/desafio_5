package com.edusync.desafio.services;

import com.edusync.desafio.models.Produto;
import com.edusync.desafio.repositories.ProdutoRepository;
import com.edusync.desafio.repositories.criteria.params.ProdutoFilterParam;
import com.edusync.desafio.repositories.criteria.ProdutoRepositoryCustom;
import com.edusync.desafio.repositories.criteria.impl.ProdutoRepositoryCustomImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoRepositoryCustomImpl produtoRepositoryCustomImpl;
    @Autowired
    private ProdutoRepositoryCustom produtoRepositoryCustom;

    public void cadastrar(Produto produto) {

        produtoRepository.save(produto);

    }

    public List<Produto> listar() {

        return produtoRepository.findAll();

    }

    public List<Produto> listarPositivo() {

        return produtoRepositoryCustomImpl.listarPositivos();

    }

    public Produto consultar(Integer codigo) {

        Optional<Produto> optProduto = produtoRepository.findById(codigo);
        if (optProduto.isEmpty()) {

            return null;
        }
        return optProduto.get();
    }

    public void update(Integer codigo, Produto produto) {
        if (produtoRepository.existsById(codigo)) {
            produtoRepository.save(produto);
        }
    }

    public void remover(Integer codigo) {

        if (produtoRepository.existsById(codigo))
            produtoRepository.deleteById(codigo);

    }
    public List<Produto> listarPorDescricao(String descricao) {

        return produtoRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

    public List<Produto> filtrar(ProdutoFilterParam param) {
        return produtoRepositoryCustom.listarCadastrados(param);
    }
}