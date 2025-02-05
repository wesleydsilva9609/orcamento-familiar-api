package br.com.alura.orcamento_familiar_api.service;

import br.com.alura.orcamento_familiar_api.dto.DadosCadastroDespesa;
import br.com.alura.orcamento_familiar_api.dto.DadosDetalhamentoDespesas;
import br.com.alura.orcamento_familiar_api.entities.Despesas;
import br.com.alura.orcamento_familiar_api.repository.DespesasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class DespesasService {
    @Autowired
    private DespesasRepository repository;

    public ResponseEntity cadastrarDespesa(DadosCadastroDespesa dadosCadastroDespesa, UriComponentsBuilder uriComponentsBuilder) {
        var despesas= new Despesas(dadosCadastroDespesa);
        var uri = uriComponentsBuilder.path("/despesas{id}").buildAndExpand(dadosCadastroDespesa.id()).toUri();
        repository.save(despesas);
        return ResponseEntity.created(uri).body(new DadosDetalhamentoDespesas(despesas));
    }

    public ResponseEntity buscarDespesaPorId(Long id) {
        var despesa = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoDespesas(despesa));
    }
}
