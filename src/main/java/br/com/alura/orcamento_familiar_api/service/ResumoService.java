package br.com.alura.orcamento_familiar_api.service;

import br.com.alura.orcamento_familiar_api.dto.resumo.CategoriaResumo;
import br.com.alura.orcamento_familiar_api.dto.resumo.ResumoDTO;
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
        // Validação: ambos precisam estar com o formato correto
        if (!ano.matches("\\d{4}") || !mes.matches("\\d{2}")) {
            return ResponseEntity.badRequest().body("Ano deve ter 4 dígitos e mês 2 dígitos.");
        }

        int anoInt = Integer.parseInt(ano);
        int mesInt = Integer.parseInt(mes);

        // Busca os valores, tratando possíveis nulls
        BigDecimal totalReceita = receitasRepository.valorTotalReceita(anoInt, mesInt);
        BigDecimal totalDespesas = despesasRepository.valorTotalDespesas(anoInt, mesInt);

        // Garante que não sejam nulos (caso não haja registros)
        if (totalReceita == null) totalReceita = BigDecimal.ZERO;
        if (totalDespesas == null) totalDespesas = BigDecimal.ZERO;

        BigDecimal saldoFinal = totalReceita.subtract(totalDespesas);

        // Busca e monta a lista de resumo por categoria
        List<Object[]> despesasCategoria = despesasRepository.despesasPorCategoria(anoInt, mesInt);
        List<CategoriaResumo> resumoList = new ArrayList<>();

        for (Object[] linha : despesasCategoria) {
            if (linha[0] != null && linha[1] != null) {
                Categoria categoria = Categoria.valueOf(linha[0].toString());
                BigDecimal total = (BigDecimal) linha[1];
                resumoList.add(new CategoriaResumo(categoria, total));
            }
        }

        ResumoDTO resumo = new ResumoDTO(totalReceita, totalDespesas, saldoFinal, resumoList);
        return ResponseEntity.ok(resumo);
    }
}
