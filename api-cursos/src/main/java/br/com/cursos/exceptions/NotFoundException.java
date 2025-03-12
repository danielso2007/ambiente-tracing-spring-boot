package br.com.cursos.exceptions;

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotFoundException(String s) {
        super(s);
    }
}