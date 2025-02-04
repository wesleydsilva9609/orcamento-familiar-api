package br.com.alura.orcamento_familiar_api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosCadastroReceita(@NotNull Long id, @NotBlank String descricao,@NotNull BigDecimal valor,@NotNull LocalDateTime data) {

}
