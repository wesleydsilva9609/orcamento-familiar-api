package br.com.alura.orcamento_familiar_api.dto.receita;

import br.com.alura.orcamento_familiar_api.entities.Receitas;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;
import java.util.Date;

public record DadosReceitaAtualizada(Long id, String descricao, BigDecimal valor, Date data) {

    @JsonCreator
    public DadosReceitaAtualizada {

    }


    public DadosReceitaAtualizada(Receitas receitas){
        this(receitas.getId(), receitas.getDescricao(),receitas.getValor(), receitas.getDataReceita());
    }

}
