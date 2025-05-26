package br.com.alura.orcamento_familiar_api.dto.despesas;

import java.math.BigDecimal;
import java.util.Date;

public record DadosDespesasAtualizadas(String descricao, BigDecimal valor, Date data) {
}
