package br.com.alura.orcamento_familiar_api.repository;

import br.com.alura.orcamento_familiar_api.entities.Receitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ReceitasRepository extends JpaRepository<Receitas,Long> {

    @Query("""
            SELECT COUNT(r) > 0 from Receitas r
            WHERE r.descricao = :descricao
            AND FUNCTION('YEAR', r.dataReceita) = FUNCTION('YEAR', :data)
            AND FUNCTION('MONTH', r.dataReceita) =FUNCTION('MONTH', :data) 
            """)
    boolean existsByDescricaoAndMes(String descricao, Date data);



    List<Receitas> findByDescricaoContainingIgnoreCase(String descricao);

    @Query("SELECT r from Receitas r WHERE FUNCTION('YEAR', r.dataReceita) = :ano AND FUNCTION('MONTH', r.dataReceita) = :mes")
    List<Receitas> findAllBuscarDataReceita(@Param("ano") String ano,@Param("mes") String mes);


    @Query("Select SUM(r.valor) from Receitas r where FUNCTION('YEAR', r.dataReceita) = :ano AND FUNCTION('MONTH', r.dataReceita) = :mes")
    BigDecimal valorTotalReceita(@Param("ano") String ano,@Param("mes") String mes);
}
