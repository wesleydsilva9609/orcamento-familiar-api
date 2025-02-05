package br.com.alura.orcamento_familiar_api.dto.despesas;

import br.com.alura.orcamento_familiar_api.entities.Despesas;

import java.math.BigDecimal;
import java.util.Date;

public record DadosDetalhamentoDespesas(Long id, String descricao, BigDecimal valor, Date data) {

    public DadosDetalhamentoDespesas(Despesas despesas){
        this(despesas.getDespesas_id(), despesas.getDespesas_descricao(), despesas.getDespesas_valor(),despesas.getDespesas_data());
    }
}
