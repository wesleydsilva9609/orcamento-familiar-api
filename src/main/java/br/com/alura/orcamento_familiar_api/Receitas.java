package br.com.alura.orcamento_familiar_api;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "Receitas")
@Table(name = "receitas")
@EqualsAndHashCode(of = "id")
public class Receitas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDateTime data_receita;

    public Receitas(){

    }


    public Receitas(DadosCadastroReceita dadosCadastroReceitareceita) {
        this.id = dadosCadastroReceitareceita.id();
        this.descricao = dadosCadastroReceitareceita.descricao();
        this.valor = dadosCadastroReceitareceita.valor();
        this.data_receita = dadosCadastroReceitareceita.data();
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getData_receita() {
        return data_receita;
    }
}
