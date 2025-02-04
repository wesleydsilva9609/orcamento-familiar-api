package br.com.alura.orcamento_familiar_api;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosDetalhamentoReceita(Long id, String descricao, BigDecimal valor, LocalDateTime data) {

    public DadosDetalhamentoReceita(Receitas receita) {
        this(receita.getId(), receita.getDescricao(), receita.getValor(),receita.getData_receita());
    }
}
