package br.com.alura.orcamento_familiar_api.dto.despesas;

import br.com.alura.orcamento_familiar_api.entities.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record DadosCadastroDespesa(Long id, Categoria categoria, @NotBlank String descricao, @NotNull BigDecimal valor, @NotNull Date data) {
}
