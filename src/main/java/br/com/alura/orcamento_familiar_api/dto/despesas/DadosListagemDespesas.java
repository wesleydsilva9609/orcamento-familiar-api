package br.com.alura.orcamento_familiar_api.dto.despesas;

import br.com.alura.orcamento_familiar_api.entities.Categoria;
import br.com.alura.orcamento_familiar_api.entities.Despesas;

import java.math.BigDecimal;
import java.util.Date;

public record DadosListagemDespesas(Categoria categoria, String descricao, BigDecimal valor, Date date) {

    public DadosListagemDespesas(Despesas despesas){
        this(despesas.getCategoria(),despesas.getDespesasDescricao(), despesas.getDespesasValor(),despesas.getDespesasData());
    }
}
