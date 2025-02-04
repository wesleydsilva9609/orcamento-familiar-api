package br.com.alura.orcamento_familiar_api.dto;

import br.com.alura.orcamento_familiar_api.entities.Receitas;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosReceitaAtualizada(Long id, String descricao, BigDecimal valor, LocalDateTime data) {

    @JsonCreator
    public DadosReceitaAtualizada {

    }


    public DadosReceitaAtualizada(Receitas receitas){
        this(receitas.getId(), receitas.getDescricao(),receitas.getValor(), receitas.getData_receita());
    }

}
