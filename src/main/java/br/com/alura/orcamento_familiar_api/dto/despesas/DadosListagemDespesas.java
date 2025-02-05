package br.com.alura.orcamento_familiar_api.dto.despesas;

import br.com.alura.orcamento_familiar_api.entities.Despesas;

import java.math.BigDecimal;
import java.util.Date;

public record DadosListagemDespesas(String descricao, BigDecimal valor, Date date) {

    public DadosListagemDespesas(Despesas despesas){
        this(despesas.getDespesas_descricao(), despesas.getDespesas_valor(),despesas.getDespesas_data());
    }
}
