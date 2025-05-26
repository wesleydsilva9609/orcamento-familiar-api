package br.com.alura.orcamento_familiar_api.dto.receita;

import br.com.alura.orcamento_familiar_api.entities.Receitas;

import java.math.BigDecimal;
import java.util.Date;

public record DadosDetalhamentoReceita(Long id, String descricao, BigDecimal valor, Date data) {

    public DadosDetalhamentoReceita(Receitas receita) {
        this(receita.getId(), receita.getDescricao(), receita.getValor(),receita.getDataReceita());
    }
}
