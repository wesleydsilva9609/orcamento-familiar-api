package br.com.alura.orcamento_familiar_api.entities;

import br.com.alura.orcamento_familiar_api.dto.receita.DadosCadastroReceita;
import br.com.alura.orcamento_familiar_api.dto.receita.DadosReceitaAtualizada;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "Receitas")
@Table(name = "receitas")
@EqualsAndHashCode(of = "id")
public class Receitas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    @Temporal(TemporalType.DATE)
    private Date dataReceita;

    public Receitas(){

    }


    public Receitas(DadosCadastroReceita dadosCadastroReceitareceita) {
        this.id = dadosCadastroReceitareceita.id();
        this.descricao = dadosCadastroReceitareceita.descricao();
        this.valor = dadosCadastroReceitareceita.valor();
        this.dataReceita = dadosCadastroReceitareceita.data();
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

    public Date getDataReceita() {
        return dataReceita;
    }

    public void atualizar(DadosReceitaAtualizada dadosReceitaAtualizada) {
        if(dadosReceitaAtualizada.descricao() != null){
            this.descricao = dadosReceitaAtualizada.descricao();
        }  if (dadosReceitaAtualizada.valor() != null) {
            this.valor = dadosReceitaAtualizada.valor();
        }if(dadosReceitaAtualizada.data() != null){
            this.dataReceita = dadosReceitaAtualizada.data();
        }
    }
}
