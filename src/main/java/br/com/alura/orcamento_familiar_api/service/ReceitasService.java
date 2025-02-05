package br.com.alura.orcamento_familiar_api.service;

import br.com.alura.orcamento_familiar_api.dto.receita.*;
import br.com.alura.orcamento_familiar_api.entities.Receitas;
import br.com.alura.orcamento_familiar_api.repository.ReceitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    public ResponseEntity<Page<DadosListagemReceita>> listarReceita(Pageable pageable) {
        var page = repository.findAll(pageable).map(DadosListagemReceita::new);
        return ResponseEntity.ok(page);
    }

    public ResponseEntity buscarporid(Long id){
        var receita = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoReceitaPorId(receita));
    }


    public ResponseEntity atualizar(Long id, DadosReceitaAtualizada dadosReceitaAtualizada) {
        var receita = repository.getReferenceById(id);
         receita.atualizar(dadosReceitaAtualizada);
        return ResponseEntity.ok(new DadosReceitaAtualizada(receita));
    }

    public ResponseEntity deletar(Long id) {
         repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
