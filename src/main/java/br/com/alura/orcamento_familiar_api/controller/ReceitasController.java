package br.com.alura.orcamento_familiar_api.controller;

import br.com.alura.orcamento_familiar_api.dto.DadosCadastroReceita;
import br.com.alura.orcamento_familiar_api.dto.DadosListagemReceita;
import br.com.alura.orcamento_familiar_api.service.ReceitasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<Page<DadosListagemReceita>> listagem(@PageableDefault(size = 8, sort = "id")Pageable pageable){
        return service.listarReceita(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarReceitaPorId(@PathVariable Long id){
        return service.buscarporid(id);
    }

}
