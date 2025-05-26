package br.com.alura.orcamento_familiar_api.dto.receita;

import br.com.alura.orcamento_familiar_api.entities.Receitas;

import java.math.BigDecimal;
import java.util.Date;

public record DadosListagemReceita(Long id,String descricao, BigDecimal valor, Date data) {

    public DadosListagemReceita(Receitas receitas){
        this(receitas.getId(), receitas.getDescricao(),receitas.getValor(),receitas.getDataReceita());
    }
}
