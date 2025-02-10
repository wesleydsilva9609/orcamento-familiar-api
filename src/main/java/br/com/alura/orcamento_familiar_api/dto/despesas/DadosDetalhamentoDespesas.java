package br.com.alura.orcamento_familiar_api.dto.despesas;

import br.com.alura.orcamento_familiar_api.entities.Categoria;
import br.com.alura.orcamento_familiar_api.entities.Despesas;

import java.math.BigDecimal;
import java.util.Date;

public record DadosDetalhamentoDespesas(Long id, Categoria categoria, String descricao, BigDecimal valor, Date data) {

    public DadosDetalhamentoDespesas(Despesas despesas){
        this(despesas.getDespesas_id(),despesas.getCategoria(), despesas.getDespesasDescricao(), despesas.getDespesasValor(),despesas.getDespesasData());
    }
}
