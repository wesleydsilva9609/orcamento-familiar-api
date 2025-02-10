package br.com.alura.orcamento_familiar_api.repository;

import br.com.alura.orcamento_familiar_api.entities.Despesas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface DespesasRepository extends JpaRepository<Despesas,Long> {
    @Query("""
            SELECT COUNT(d) > 0  from Despesas d 
            WHERE d.despesasDescricao = :descricao 
             AND FUNCTION('YEAR', d.despesasData) = FUNCTION('YEAR', :data) 
            AND FUNCTION('MONTH', d.despesasData) =  FUNCTION('MONTH', :data)
            """)
    boolean existsByDescricaoAndMes(String descricao, Date data);

    List<Despesas> findByDespesasDescricaoContainingIgnoreCase(String descricao);

    @Query("Select d from Despesas d WHERE FUNCTION('YEAR', d.despesasData) = :ano AND FUNCTION('MONTH', d.despesasData) = :mes")
    List<Despesas> buscarPordata(@Param("ano") String ano,@Param("mes") String mes);


    @Query("Select SUM(d.despesasValor) from Despesas d WHERE FUNCTION('YEAR', d.despesasData) = :ano AND FUNCTION('MONTH',d.despesasData) = :mes")
    BigDecimal valorTotalDespesas(@Param("ano") String ano,@Param("mes") String mes);

    @Query("Select d.categoria, SUM(d.despesasValor) from Despesas d WHERE FUNCTION('YEAR', d.despesasData) = :ano AND FUNCTION('MONTH', d.despesasData) = :mes GROUP BY d.categoria")
    List<Object[]> despesasPorCategoria(String ano, String mes);
}
