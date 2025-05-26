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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceitasService {
    @Autowired
    private ReceitasRepository repository;

    public ResponseEntity cadastrarReceita(DadosCadastroReceita dadosCadastroReceitareceita, UriComponentsBuilder uriComponentsBuilder) {
        boolean existeduplicada = repository.existsByDescricaoAndMes(dadosCadastroReceitareceita.descricao(),dadosCadastroReceitareceita.data());

        if(existeduplicada){
            return ResponseEntity.badRequest().body("Ja existe uma receita com essa descrição nesse mes");
        }

        var receita = new Receitas(dadosCadastroReceitareceita);
        var uri = uriComponentsBuilder
                .path("/receitas/{id}").buildAndExpand(dadosCadastroReceitareceita.id())
                .toUri();
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

    public ResponseEntity <List<DadosListagemReceita>> listarReceitaPorNome(String descricao) {
        // Verifica se a descrição é nula ou vazia antes de buscar no banco
        if(descricao == null || descricao.trim().isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var receita = repository.findByDescricaoContainingIgnoreCase(descricao);
        // Se não encontrar receitas, retorna 404 Not Found
        if(receita.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(conversor(receita));
    }

    public List<DadosListagemReceita> conversor(List<Receitas> receitasList){
      return  receitasList.stream().map(DadosListagemReceita::new).collect(Collectors.toList());
    }

    public ResponseEntity<List<DadosListagemReceita>> ListarPorData(String ano, String mes) {
        if (!ano.matches("\\d{4}") || !mes.matches("\\d{1,2}")){
            return  ResponseEntity.badRequest().build();
        }
        int anoStr = Integer.parseInt(ano);
        int mesStr = Integer.parseInt(mes);

        var receita = repository.findAllBuscarDataReceita(anoStr,mesStr);

        if(receita.isEmpty()){
         return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(conversor(receita));
    }
}
