package br.com.alura.orcamento_familiar_api.repository;

import br.com.alura.orcamento_familiar_api.entities.Receitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface ReceitasRepository extends JpaRepository<Receitas,Long> {

    @Query("""
            SELECT COUNT(r) > 0 from Receitas r
            WHERE r.descricao = :descricao
            AND FUNCTION('YEAR', r.data_receita) = FUNCTION('YEAR', :data)
            AND FUNCTION('MONTH', r.data_receita) =FUNCTION('MONTH', :data) 
            """)
    boolean existsByDescricaoAndMes(String descricao, Date data);
}
