package br.com.alura.orcamento_familiar_api.service;
import br.com.alura.orcamento_familiar_api.dto.despesas.DadosCadastroDespesa;
import br.com.alura.orcamento_familiar_api.dto.despesas.DadosDespesasAtualizadas;
import br.com.alura.orcamento_familiar_api.dto.despesas.DadosDetalhamentoDespesas;
import br.com.alura.orcamento_familiar_api.dto.despesas.DadosListagemDespesas;
import br.com.alura.orcamento_familiar_api.dto.receita.DadosListagemReceita;
import br.com.alura.orcamento_familiar_api.entities.Despesas;
import br.com.alura.orcamento_familiar_api.repository.DespesasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DespesasService {
    @Autowired
    private DespesasRepository repository;

    public ResponseEntity cadastrarDespesa(DadosCadastroDespesa dadosCadastroDespesa, UriComponentsBuilder uriComponentsBuilder) {
        // Extrai a data da despesa (tipo java.util.Date)
        Date data = dadosCadastroDespesa.data();

        // Converte para LocalDate para extrair ano e mês de forma segura
        LocalDate localDate = data.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        int ano = localDate.getYear();
        int mes = localDate.getMonthValue();

        // Verifica se já existe despesa com mesma descrição no mesmo mês/ano
        boolean existeDuplicada = repository.existsByDescricaoAndMes(dadosCadastroDespesa.descricao(), ano, mes);

        if (existeDuplicada) {
            return ResponseEntity.badRequest().body("Já existe uma despesa com essa descrição nesse mês");
        }

        // Cria e salva a despesa
        var despesas = new Despesas(dadosCadastroDespesa);
        repository.save(despesas); // salva antes de montar o URI para pegar o ID gerado

        // Cria a URI para o recurso criado
        var uri = uriComponentsBuilder
                .path("/despesas/{id}")
                .buildAndExpand(despesas.getDespesas_id())
                .toUri();

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

    public ResponseEntity<List<DadosListagemDespesas>> listarDespesasPorDescricao(String descricao) {
        if(descricao == null || descricao.trim().isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var despesas = repository.findByDespesasDescricaoContainingIgnoreCase(descricao);

        if (despesas.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(conversor(despesas));
    }

    public List<DadosListagemDespesas> conversor(List<Despesas> despesasList){
        return despesasList.stream().map(DadosListagemDespesas::new).collect(Collectors.toList());
    }

    public ResponseEntity<List<DadosListagemDespesas>> ListarPorData(String ano, String mes) {
        // Verifica se os parâmetros ano e mes possuem o formato correto (ano = 4 dígitos, mes = 2 dígitos)
        if(!ano.matches("\\d{4}") || !mes.matches("\\d{2}")){
            return ResponseEntity.badRequest().build();
        }

        int anosrt = Integer.parseInt(ano);
        int messrt = Integer.parseInt(mes);
        var despesas = repository.buscarPordata(anosrt,messrt);

        if(despesas.isEmpty()){
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(conversor(despesas));
    }
}
