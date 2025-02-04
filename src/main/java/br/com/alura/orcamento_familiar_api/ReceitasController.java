package br.com.alura.orcamento_familiar_api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/receitas")
public class ReceitasController {
    @Autowired
    private ReceitasService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarReceita(@RequestBody @Valid DadosCadastroReceita receita, UriComponentsBuilder uriComponentsBuilder){
        return service.cadastrarReceita(receita,uriComponentsBuilder);
    }
}
