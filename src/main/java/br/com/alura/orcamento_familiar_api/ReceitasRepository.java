package br.com.alura.orcamento_familiar_api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitasRepository extends JpaRepository<Receitas,Long> {
}
