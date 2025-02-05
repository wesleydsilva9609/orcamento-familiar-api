package br.com.alura.orcamento_familiar_api.controller;

import br.com.alura.orcamento_familiar_api.dto.DadosCadastroDespesa;
import br.com.alura.orcamento_familiar_api.service.DespesasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/despesas")
public class DespesasController {
    @Autowired
    private DespesasService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarDespesa(@RequestBody @Valid DadosCadastroDespesa dadosCadastroDespesa, UriComponentsBuilder uriComponentsBuilder){
        return service.cadastrarDespesa(dadosCadastroDespesa,uriComponentsBuilder);
    }

    @GetMapping("/{id}")
    public ResponseEntity BuscarDespesaPorId(@PathVariable Long id){
        return service.buscarDespesaPorId(id);
    }


}
