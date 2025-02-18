package br.com.alura.orcamento_familiar_api.infra.exceptions;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
