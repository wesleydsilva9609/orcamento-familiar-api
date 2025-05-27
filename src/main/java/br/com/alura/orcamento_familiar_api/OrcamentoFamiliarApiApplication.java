package br.com.alura.orcamento_familiar_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("br.com.alura.orcamento_familiar_api.entities")
@EnableJpaRepositories("br.com.alura.orcamento_familiar_api.repository")
public class OrcamentoFamiliarApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrcamentoFamiliarApiApplication.class, args);
	}

}
