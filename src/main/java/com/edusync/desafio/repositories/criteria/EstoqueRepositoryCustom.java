package com.edusync.desafio.repositories.criteria;

import com.edusync.desafio.models.EstoqueMovimento;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepositoryCustom {

    List<EstoqueMovimento> listarHistorico(Integer codigo);
}
