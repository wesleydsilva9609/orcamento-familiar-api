package br.com.alura.orcamento_familiar_api.repository;

import br.com.alura.orcamento_familiar_api.entities.Despesas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface DespesasRepository extends JpaRepository<Despesas,Long> {
    @Query("""
            SELECT COUNT(d) > 0  from Despesas d 
            WHERE d.despesas_descricao = :descricao 
             AND FUNCTION('YEAR', d.despesas_data) = FUNCTION('YEAR', :data) 
            AND FUNCTION('MONTH', d.despesas_data) =  FUNCTION('MONTH', :data)
            """)
    boolean existsByDescricaoAndMes(String descricao, Date data);
}
