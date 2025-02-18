package br.com.alura.orcamento_familiar_api.infra.exceptions;

import br.com.alura.orcamento_familiar_api.infra.exceptions.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratamentoErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratamentoErro400(MethodArgumentNotValidException methodArgumentNotValidException){
        var erros = methodArgumentNotValidException.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());

    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tramentoErro400Validacao(ValidacaoException exception){
        return ResponseEntity.badRequest().body(exception);
    }

    private record DadosErroValidacao(String campo, String mensagem){

        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }

    }

}
