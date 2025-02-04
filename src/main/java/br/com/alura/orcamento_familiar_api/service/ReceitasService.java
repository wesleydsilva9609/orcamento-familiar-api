package br.com.alura.orcamento_familiar_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ReceitasService {
    @Autowired
    private ReceitasRepository repository;

    public ResponseEntity cadastrarReceita(DadosCadastroReceita dadosCadastroReceitareceita, UriComponentsBuilder uriComponentsBuilder) {
        var receita = new Receitas(dadosCadastroReceitareceita);
        var uri = uriComponentsBuilder.path("/receitas/{id}").buildAndExpand(dadosCadastroReceitareceita.id()).toUri();
        repository.save(receita);
        return ResponseEntity.created(uri).body(new DadosDetalhamentoReceita(receita));
    }



}
