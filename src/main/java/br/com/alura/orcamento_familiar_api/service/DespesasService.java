package br.com.alura.orcamento_familiar_api.service;
import br.com.alura.orcamento_familiar_api.dto.despesas.DadosCadastroDespesa;
import br.com.alura.orcamento_familiar_api.dto.despesas.DadosDespesasAtualizadas;
import br.com.alura.orcamento_familiar_api.dto.despesas.DadosDetalhamentoDespesas;
import br.com.alura.orcamento_familiar_api.dto.despesas.DadosListagemDespesas;
import br.com.alura.orcamento_familiar_api.entities.Despesas;
import br.com.alura.orcamento_familiar_api.repository.DespesasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class DespesasService {
    @Autowired
    private DespesasRepository repository;

    public ResponseEntity cadastrarDespesa(DadosCadastroDespesa dadosCadastroDespesa, UriComponentsBuilder uriComponentsBuilder) {
        // Verifica se já existe uma despesa com a mesma descrição no mesmo mês
        boolean existeDuplicada = repository.existsByDescricaoAndMes(dadosCadastroDespesa.descricao(),dadosCadastroDespesa.data());

        if(existeDuplicada){
            return ResponseEntity.badRequest().body("Ja existe uma despesa com essa descrição nesse mes");
        }

        var despesas= new Despesas(dadosCadastroDespesa);
        var uri = uriComponentsBuilder.path("/despesas{id}").buildAndExpand(dadosCadastroDespesa.id()).toUri();
        repository.save(despesas);
        return ResponseEntity.created(uri).body(new DadosDetalhamentoDespesas(despesas));
    }

    public ResponseEntity buscarDespesaPorId(Long id) {
        var despesa = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoDespesas(despesa));
    }

    public ResponseEntity<Page<DadosListagemDespesas>> listarDespesas(Pageable pageable) {
        var page = repository.findAll(pageable).map(DadosListagemDespesas::new);
        return ResponseEntity.ok(page);
    }

    public ResponseEntity atualizarDespesas(Long id, DadosDespesasAtualizadas dadosDespesasAtualizadas){
        var despesas = repository.getReferenceById(id);
        despesas.atualizar(dadosDespesasAtualizadas);
        return ResponseEntity.ok(new DadosDetalhamentoDespesas(despesas));
    }

    public ResponseEntity deletarPorId(Long id) {
       repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
