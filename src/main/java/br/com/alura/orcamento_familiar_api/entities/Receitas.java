package br.com.alura.orcamento_familiar_api.entities;

import br.com.alura.orcamento_familiar_api.dto.DadosCadastroReceita;
import br.com.alura.orcamento_familiar_api.dto.DadosReceitaAtualizada;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

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

    public void atualizar(DadosReceitaAtualizada dadosReceitaAtualizada) {
        if(dadosReceitaAtualizada.descricao() != null){
            this.descricao = dadosReceitaAtualizada.descricao();
        }  if (dadosReceitaAtualizada.valor() != null) {
            this.valor = dadosReceitaAtualizada.valor();
        }if(dadosReceitaAtualizada.data() != null){
            this.data_receita = dadosReceitaAtualizada.data();
        }
    }
}
