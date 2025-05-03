package br.com.cursos.infrastructure.exceptions;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5359235925273673341L;

    public NotFoundException(String msg) {
        super(msg);
    }
}