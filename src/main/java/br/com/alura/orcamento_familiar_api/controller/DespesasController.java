package br.com.alura.orcamento_familiar_api.controller;

import br.com.alura.orcamento_familiar_api.dto.despesas.DadosCadastroDespesa;
import br.com.alura.orcamento_familiar_api.dto.despesas.DadosDespesasAtualizadas;
import br.com.alura.orcamento_familiar_api.dto.despesas.DadosListagemDespesas;
import br.com.alura.orcamento_familiar_api.dto.receita.DadosListagemReceita;
import br.com.alura.orcamento_familiar_api.service.DespesasService;
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
@RequestMapping("/despesas")
@SecurityRequirement(name = "bearer-key")
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

    @GetMapping
    public ResponseEntity<Page<DadosListagemDespesas>> listar(@PageableDefault(size = 8) Pageable pageable){
        return service.listarDespesas(pageable);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarDespesas(@PathVariable Long id,@RequestBody DadosDespesasAtualizadas dadosDespesasAtualizadas){
        return service.atualizarDespesas(id,dadosDespesasAtualizadas);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarDespesaPorId(@PathVariable Long id){
        return service.deletarPorId(id);
    }

    @GetMapping(params = "descricao")
    public ResponseEntity<List<DadosListagemDespesas>> listarPorDescricao(@RequestParam(name = "descricao",required = false,defaultValue = "")String descricao){
        return service.listarDespesasPorDescricao(descricao);
    }

    @GetMapping("/{ano}/{mes}")
    ResponseEntity<List<DadosListagemDespesas>> listarPorData(@PathVariable String ano, @PathVariable String mes){
        return service.ListarPorData(ano,mes);
    }


}
