package br.com.alura.orcamento_familiar_api.dto;

import br.com.alura.orcamento_familiar_api.entities.Despesas;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.math.BigDecimal;
import java.util.List;

public record ResumoDTO(BigDecimal receita, BigDecimal despesas, BigDecimal saldoFinal, List<CategoriaResumo> categorias) {
}
