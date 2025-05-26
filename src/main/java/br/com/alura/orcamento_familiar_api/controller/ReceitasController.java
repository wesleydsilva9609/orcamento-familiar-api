package br.com.alura.orcamento_familiar_api.controller;

import br.com.alura.orcamento_familiar_api.dto.receita.DadosCadastroReceita;
import br.com.alura.orcamento_familiar_api.dto.receita.DadosListagemReceita;
import br.com.alura.orcamento_familiar_api.dto.receita.DadosReceitaAtualizada;
import br.com.alura.orcamento_familiar_api.service.ReceitasService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/receitas")
@SecurityRequirement(name = "bearer-key")
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

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarReceitaPorId(@PathVariable Long id,@RequestBody @Valid DadosReceitaAtualizada dadosReceitaAtualizada){
        return service.atualizar(id,dadosReceitaAtualizada);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarReceita(@PathVariable Long id){
        return service.deletar(id);
    }

    @GetMapping(params = "descricao")
    public ResponseEntity<List<DadosListagemReceita>> listarPorNome(@RequestParam(name = "descricao", required = false, defaultValue = "") String descricao){
        return service.listarReceitaPorNome(descricao);
    }

    @GetMapping("/{ano}/{mes}")
    ResponseEntity<List<DadosListagemReceita>> listarPorData(@PathVariable String ano, @PathVariable String mes){
        return service.ListarPorData(ano,mes);
    }

}
