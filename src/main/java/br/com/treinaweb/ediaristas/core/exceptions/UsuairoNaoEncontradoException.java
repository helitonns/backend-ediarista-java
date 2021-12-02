package br.com.treinaweb.ediaristas.core.exceptions;

import javax.persistence.EntityNotFoundException;

public class UsuairoNaoEncontradoException extends EntityNotFoundException {

    public UsuairoNaoEncontradoException(String message) {
        super(message);
    }

}
