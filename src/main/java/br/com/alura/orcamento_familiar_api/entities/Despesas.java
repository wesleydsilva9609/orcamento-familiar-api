package br.com.alura.orcamento_familiar_api.entities;

import br.com.alura.orcamento_familiar_api.dto.despesas.DadosCadastroDespesa;
import br.com.alura.orcamento_familiar_api.dto.despesas.DadosDespesasAtualizadas;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "Despesas")
@Table(name = "despesas")
public class Despesas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long despesas_id;
    private String despesasDescricao;
    private BigDecimal despesasValor;
    private Date despesasData;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;



    public Despesas() {
    }

    public Despesas(Long despesas_id, String despesasDescricao, BigDecimal despesasValor, Date despesasData, Categoria categoria) {
        this.despesas_id = despesas_id;
        this.categoria = categoria;
        this.despesasDescricao = despesasDescricao;
        this.despesasValor = despesasValor;
        this.despesasData = despesasData;
    }

    public Despesas(DadosCadastroDespesa dadosCadastroDespesa) {
        this.despesas_id = dadosCadastroDespesa.id();
        this.categoria = dadosCadastroDespesa.categoria();
        this.despesasDescricao = dadosCadastroDespesa.descricao();
        this.despesasValor = dadosCadastroDespesa.valor();
        this.despesasData = dadosCadastroDespesa.data();
    }

    public void atualizar(DadosDespesasAtualizadas dadosDespesasAtualizadas){
        if(dadosDespesasAtualizadas.descricao() != null){
            this.despesasDescricao = dadosDespesasAtualizadas.descricao();
        }if(dadosDespesasAtualizadas.valor() != null){
            this.despesasValor = dadosDespesasAtualizadas.valor();
        }if(dadosDespesasAtualizadas.data() != null){
            this.despesasData = dadosDespesasAtualizadas.data();
        }

    }

    public Long getDespesas_id() {
        return despesas_id;
    }

    public String getDespesasDescricao() {
        return despesasDescricao;
    }

    public BigDecimal getDespesasValor() {
        return despesasValor;
    }

    public Date getDespesasData() {
        return despesasData;
    }

    public Categoria getCategoria() {
        return categoria;
    }


}
