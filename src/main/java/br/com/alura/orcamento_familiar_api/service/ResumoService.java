package br.com.alura.orcamento_familiar_api.service;

import br.com.alura.orcamento_familiar_api.dto.CategoriaResumo;
import br.com.alura.orcamento_familiar_api.dto.ResumoDTO;
import br.com.alura.orcamento_familiar_api.entities.Categoria;
import br.com.alura.orcamento_familiar_api.repository.DespesasRepository;
import br.com.alura.orcamento_familiar_api.repository.ReceitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResumoService {
    @Autowired
    private DespesasRepository despesasRepository;
    @Autowired
    private ReceitasRepository receitasRepository;

    public ResponseEntity resumofiltro(String ano, String mes) {
        //verifica se ano e mes estão no formato correto
        if(!ano.matches("\\d{4}") && !mes.matches("\\d{2}")){
            return ResponseEntity.badRequest().build();
        }
        var totalReceita = receitasRepository.valorTotalReceita(ano,mes);
        var totalDespesas = despesasRepository.valorTotalDespesas(ano,mes);
        var saldoFinal = totalReceita.subtract(totalDespesas);

        List<Object[]> despesasCategoria = despesasRepository.despesasPorCategoria(ano,mes);
        List<CategoriaResumo> resumoList = new ArrayList<>();

        for (Object[] linha : despesasCategoria) {
            if(linha[0] != null && linha[1] != null) {
                Categoria categoria = Categoria.valueOf(linha[0].toString());
                BigDecimal total = (BigDecimal) linha[1];
                resumoList.add(new CategoriaResumo(categoria, total));
            }

        }

        return ResponseEntity.ok(new ResumoDTO(totalReceita,totalDespesas,saldoFinal,resumoList));
    }
}
