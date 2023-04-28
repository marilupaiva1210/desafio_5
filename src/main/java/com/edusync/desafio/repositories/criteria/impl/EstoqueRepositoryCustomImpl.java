package com.edusync.desafio.repositories.criteria.impl;

import com.edusync.desafio.models.EstoqueMovimento;
import com.edusync.desafio.repositories.criteria.EstoqueRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EstoqueRepositoryCustomImpl implements EstoqueRepositoryCustom {
    private EntityManager entityManager;

    public EstoqueRepositoryCustomImpl(EntityManager manager) {
        this.entityManager = manager;
    }

    @Override
    public List<EstoqueMovimento> listarHistorico(Integer codigo) {

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<EstoqueMovimento> query = criteriaBuilder.createQuery(EstoqueMovimento.class);

        Root<EstoqueMovimento> estoqueMovimento = query.from(EstoqueMovimento.class);

        if (codigo != null) {
            query.select(estoqueMovimento).where(criteriaBuilder.equal(estoqueMovimento.get("produto"), codigo));
        }

        TypedQuery<EstoqueMovimento> typedResult = this.entityManager.createQuery(query);
        return typedResult.getResultList();
    }
}