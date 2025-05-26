package br.com.alura.orcamento_familiar_api.dto.resumo;

import br.com.alura.orcamento_familiar_api.dto.resumo.CategoriaResumo;

import java.math.BigDecimal;
import java.util.List;

public record ResumoDTO(BigDecimal receita, BigDecimal despesas, BigDecimal saldoFinal, List<CategoriaResumo> categorias) {
}
