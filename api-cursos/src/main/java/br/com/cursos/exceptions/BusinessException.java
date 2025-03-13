package br.com.cursos.exceptions;

import java.io.Serial;

public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6772675191244543989L;

    public BusinessException(String msg) {
        super(msg);
    }
}