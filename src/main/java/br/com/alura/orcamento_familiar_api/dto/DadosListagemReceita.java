package br.com.alura.orcamento_familiar_api.dto;

import br.com.alura.orcamento_familiar_api.entities.Receitas;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosListagemReceita(Long id,String descricao, BigDecimal valor, LocalDateTime data) {

    public DadosListagemReceita(Receitas receitas){
        this(receitas.getId(), receitas.getDescricao(),receitas.getValor(),receitas.getData_receita());
    }
}
