package br.com.treinaweb.ediaristas.core.exceptions;

import org.springframework.validation.FieldError;

public class UsuarioCadastradoException extends ValidacaoException {

    public UsuarioCadastradoException(String message, FieldError fieldError) {
        super(message, fieldError);
    }

}
