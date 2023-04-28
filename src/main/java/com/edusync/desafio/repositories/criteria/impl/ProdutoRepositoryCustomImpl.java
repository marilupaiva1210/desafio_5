package com.edusync.desafio.repositories.criteria.impl;

import com.edusync.desafio.models.Produto;
import com.edusync.desafio.repositories.criteria.params.ProdutoFilterParam;
import com.edusync.desafio.repositories.criteria.ProdutoRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ProdutoRepositoryCustomImpl implements ProdutoRepositoryCustom {

    private EntityManager entityManager;

    public ProdutoRepositoryCustomImpl(EntityManager manager) {
        this.entityManager = manager;
    }

    @Override
    public List<Produto> listarPositivos() {

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> query = criteriaBuilder.createQuery(Produto.class);

        Root<Produto> produto = query.from(Produto.class);

        query.select(produto).where(criteriaBuilder.gt(produto.get("saldoAtual"), 0));

        TypedQuery<Produto> typedResult = this.entityManager.createQuery(query);
        return typedResult.getResultList();
    }

    @Override
    public List<Produto> listarCadastrados(ProdutoFilterParam params) {

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> query = criteriaBuilder.createQuery(Produto.class);

        Root<Produto> produto= query.from(Produto.class);
        List<Predicate> predicates = new ArrayList<>();


        if (params.getDescricao() != null) {
            predicates.add(criteriaBuilder.like(produto.get("descricao"), "%" + params.getDescricao() + "%"));
        }

        if (params.getPrecoVenda() != null) {
            predicates.add(criteriaBuilder.equal(produto.get("precoVenda"), params.getPrecoVenda()));
        }

        if (params.getSaldoAtual() != null) {
            predicates.add(criteriaBuilder.equal(produto.get("saldoAtual"), params.getSaldoAtual()));
        }

        if (!predicates.isEmpty()) {
            query.where(predicates.stream().toArray(Predicate[]::new));
        }


        TypedQuery<Produto> typedResult = this.entityManager.createQuery(query);
        return typedResult.getResultList();
    }

}
