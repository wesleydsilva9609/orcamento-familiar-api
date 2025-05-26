package br.com.alura.orcamento_familiar_api.repository;

import br.com.alura.orcamento_familiar_api.entities.Despesas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface DespesasRepository extends JpaRepository<Despesas, Long> {

    // Verifica se existe despesa com a descrição no mesmo ano e mês da data passada
    @Query(value = """
    SELECT COUNT(*) > 0 FROM despesas d
    WHERE d.despesas_descricao = :descricao
      AND EXTRACT(YEAR FROM d.despesas_data) = :ano
      AND EXTRACT(MONTH FROM d.despesas_data) = :mes
    """, nativeQuery = true)
    boolean existsByDescricaoAndMes(
            @Param("descricao") String descricao,
            @Param("ano") int ano,
            @Param("mes") int mes
    );

    // Busca despesas cujo descrição contém o texto (JPQL pode continuar normal aqui)
    List<Despesas> findByDespesasDescricaoContainingIgnoreCase(String descricao);

    // Busca despesas por ano e mês (native query com EXTRACT)
    @Query(value = """
        SELECT * FROM despesas d
        WHERE EXTRACT(YEAR FROM d.despesas_data) = :ano
          AND EXTRACT(MONTH FROM d.despesas_data) = :mes
        """, nativeQuery = true)
    List<Despesas> buscarPordata(@Param("ano") int ano, @Param("mes") int mes);

    // Soma total das despesas para ano e mês informados
    @Query(value = """
        SELECT SUM(d.despesas_valor) FROM despesas d
        WHERE EXTRACT(YEAR FROM d.despesas_data) = :ano
          AND EXTRACT(MONTH FROM d.despesas_data) = :mes
        """, nativeQuery = true)
    BigDecimal valorTotalDespesas(@Param("ano") int ano, @Param("mes") int mes);

    // Agrupa despesas por categoria, somando o valor, para ano e mês
    @Query(value = """
        SELECT d.categoria, SUM(d.despesas_valor) FROM despesas d
        WHERE EXTRACT(YEAR FROM d.despesas_data) = :ano
          AND EXTRACT(MONTH FROM d.despesas_data) = :mes
        GROUP BY d.categoria
        """, nativeQuery = true)
    List<Object[]> despesasPorCategoria(@Param("ano") int ano, @Param("mes") int mes);

}
