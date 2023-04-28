package com.edusync.desafio.repositories.criteria;

import com.edusync.desafio.models.Produto;
import com.edusync.desafio.repositories.criteria.params.ProdutoFilterParam;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProdutoRepositoryCustom {

    List<Produto> listarPositivos();

    List<Produto> listarCadastrados(ProdutoFilterParam params);
}
