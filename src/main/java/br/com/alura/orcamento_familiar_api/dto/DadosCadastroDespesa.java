package br.com.alura.orcamento_familiar_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record DadosCadastroDespesa(Long id, @NotBlank String descricao, @NotNull BigDecimal valor, @NotNull Date data) {
}
