package br.com.alura.orcamento_familiar_api.entities;

import br.com.alura.orcamento_familiar_api.dto.DadosCadastroDespesa;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

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

    public Despesas() {
    }

    public Despesas(Long despesas_id, String despesas_descricao, BigDecimal despesas_valor, Date despesas_data) {
        this.despesas_id = despesas_id;
        this.despesas_descricao = despesas_descricao;
        this.despesas_valor = despesas_valor;
        this.despesas_data = despesas_data;
    }

    public Despesas(DadosCadastroDespesa dadosCadastroDespesa) {
        this.despesas_id = dadosCadastroDespesa.id();
        this.despesas_descricao = dadosCadastroDespesa.descricao();
        this.despesas_valor = dadosCadastroDespesa.valor();
        this.despesas_data = dadosCadastroDespesa.data();
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
}
