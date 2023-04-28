package com.edusync.desafio.repositories;

import com.edusync.desafio.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    public List<Produto> findByDescricaoContainingIgnoreCase(String descricao);
    public Produto findByDescricaoAndPrecoVenda(String descricao, Double precoVenda);

}
