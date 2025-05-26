package br.com.alura.orcamento_familiar_api.repository;

import br.com.alura.orcamento_familiar_api.entities.Receitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ReceitasRepository extends JpaRepository<Receitas, Long> {

    @Query(value = """
            SELECT COUNT(*) > 0 FROM receitas r
            WHERE r.descricao = :descricao
              AND EXTRACT(YEAR FROM r.data_receita) = EXTRACT(YEAR FROM CAST(:data AS DATE))
              AND EXTRACT(MONTH FROM r.data_receita) = EXTRACT(MONTH FROM CAST(:data AS DATE))
            """, nativeQuery = true)
    boolean existsByDescricaoAndMes(@Param("descricao") String descricao, @Param("data") Date data);

    List<Receitas> findByDescricaoContainingIgnoreCase(String descricao);

    @Query(value = """
    SELECT * FROM receitas r
    WHERE EXTRACT(YEAR FROM r.data_receita) = :ano
      AND EXTRACT(MONTH FROM r.data_receita) = :mes
    """, nativeQuery = true)
    List<Receitas> findAllBuscarDataReceita(@Param("ano") int ano, @Param("mes") int mes);


    @Query(value = """
            SELECT COALESCE(SUM(r.valor), 0) FROM receitas r
            WHERE EXTRACT(YEAR FROM r.data_receita) = :ano
              AND EXTRACT(MONTH FROM r.data_receita) = :mes
            """, nativeQuery = true)
    BigDecimal valorTotalReceita(@Param("ano") int ano, @Param("mes") int mes);
}
