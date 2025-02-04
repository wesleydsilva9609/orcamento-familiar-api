package br.com.alura.orcamento_familiar_api.dto;

import br.com.alura.orcamento_familiar_api.entities.Receitas;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosDetalhamentoReceitaPorId(String descricao, BigDecimal valor, LocalDateTime data) {

    public DadosDetalhamentoReceitaPorId(Receitas receita) {
        this(receita.getDescricao(),receita.getValor(),receita.getData_receita());
    }
}
