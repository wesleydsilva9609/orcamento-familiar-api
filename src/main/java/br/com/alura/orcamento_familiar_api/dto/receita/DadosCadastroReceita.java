package br.com.alura.orcamento_familiar_api.dto.receita;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public record DadosCadastroReceita(@NotNull Long id, @NotBlank String descricao,@NotNull BigDecimal valor,@NotNull Date data) {

}
