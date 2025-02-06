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
    private String despesas_descricao;
    private BigDecimal despesas_valor;
    private Date despesas_data;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;



    public Despesas() {
    }

    public Despesas(Long despesas_id, String despesas_descricao, BigDecimal despesas_valor, Date despesas_data,Categoria categoria) {
        this.despesas_id = despesas_id;
        this.categoria = categoria;
        this.despesas_descricao = despesas_descricao;
        this.despesas_valor = despesas_valor;
        this.despesas_data = despesas_data;
    }

    public Despesas(DadosCadastroDespesa dadosCadastroDespesa) {
        this.despesas_id = dadosCadastroDespesa.id();
        this.categoria = dadosCadastroDespesa.categoria();
        this.despesas_descricao = dadosCadastroDespesa.descricao();
        this.despesas_valor = dadosCadastroDespesa.valor();
        this.despesas_data = dadosCadastroDespesa.data();
    }

    public void atualizar(DadosDespesasAtualizadas dadosDespesasAtualizadas){
        if(dadosDespesasAtualizadas.descricao() != null){
            this.despesas_descricao = dadosDespesasAtualizadas.descricao();
        }if(dadosDespesasAtualizadas.valor() != null){
            this.despesas_valor = dadosDespesasAtualizadas.valor();
        }if(dadosDespesasAtualizadas.data() != null){
            this.despesas_data = dadosDespesasAtualizadas.data();
        }

    }

    public Long getDespesas_id() {
        return despesas_id;
    }

    public String getDespesas_descricao() {
        return despesas_descricao;
    }

    public BigDecimal getDespesas_valor() {
        return despesas_valor;
    }

    public Date getDespesas_data() {
        return despesas_data;
    }

    public Categoria getCategoria() {
        return categoria;
    }


}
