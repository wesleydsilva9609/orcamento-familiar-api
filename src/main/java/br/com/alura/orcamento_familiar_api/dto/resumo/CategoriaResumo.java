package br.com.alura.orcamento_familiar_api.dto.resumo;

import br.com.alura.orcamento_familiar_api.entities.Categoria;

import java.math.BigDecimal;

public record CategoriaResumo(Categoria categoria, BigDecimal total) {
}
