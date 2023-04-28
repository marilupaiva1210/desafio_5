package com.edusync.desafio.services;

import com.edusync.desafio.models.EstoqueMovimento;
import com.edusync.desafio.models.Produto;
import com.edusync.desafio.repositories.EstoqueMovimentoRepository;
import com.edusync.desafio.repositories.criteria.EstoqueRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueMovimentoService {

    @Autowired
    private EstoqueMovimentoRepository estoqueMovimentoRepository;
    @Autowired
    private EstoqueRepositoryCustom estoqueRepositoryCustom;

    @Autowired
    private ProdutoService produtoService;

    public void cadastrar(EstoqueMovimento movimento) {
                                                            //achei o cod do produto em movimento
        Produto produto = produtoService.consultar(movimento.getProduto().getCodigo());

            //se estiver entrando
        if (movimento.getTipoMovimento().equals("E")) {

            //pego um produto           //pego sua quantia antiga       //somo com o atual
            produto.setSaldoAtual(produto.getSaldoAtual() + movimento.getQuantidade());
            produtoService.update(produto.getCodigo(), produto);
        } else {
            //pego um produto           //pego sua quantia antiga       //subtrair com o atual
            produto.setSaldoAtual(produto.getSaldoAtual() - movimento.getQuantidade());
            produtoService.update(produto.getCodigo(), produto);
        }

        estoqueMovimentoRepository.save(movimento);

}

    public List<EstoqueMovimento> listarMovimento() {

        return estoqueMovimentoRepository.findAll();
    }

    public void update(Integer codigo, EstoqueMovimento movimento) {
        if (estoqueMovimentoRepository.existsById(codigo)) {
            estoqueMovimentoRepository.save(movimento);
        }
    }

    public EstoqueMovimento pesquisar(Integer codigo) {

        Optional<EstoqueMovimento> optMovimento = estoqueMovimentoRepository.findById(codigo);
        if (optMovimento.isEmpty()) {
            return null;
        }
        return optMovimento.get();
    }

    public void remover(Integer codigo) {
        if (estoqueMovimentoRepository.existsById(codigo)){
            estoqueMovimentoRepository.deleteById(codigo);
        }
    }
    public List<EstoqueMovimento> listarMovimentoPorHistorico(Integer produtoId) {

        return estoqueRepositoryCustom.listarHistorico(produtoId);

    }
}

