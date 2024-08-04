package br.com.ifpe.oxefoodapithyago.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

  @ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ClienteException extends RuntimeException {

    public ClienteException(String entidade, Long id) {
	super(String.format("Não foi encontrado(a) um(a) %s com o id %s", entidade, id.toString()));
    }
}

  
