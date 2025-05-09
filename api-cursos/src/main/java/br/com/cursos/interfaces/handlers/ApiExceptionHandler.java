package br.com.cursos.interfaces.handlers;

import br.com.cursos.infrastructure.exceptions.BusinessException;
import br.com.cursos.infrastructure.exceptions.InternalErrorException;
import br.com.cursos.infrastructure.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.Instant;

@SuppressWarnings({"PMD.GuardLogStatement"})
@RestControllerAdvice
public class ApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        ProblemDetail problem = new ProblemDetail();
        problem.setType(request.getRequestURL().toString());
        problem.setTitle("Argumento do método não válido");
        problem.setStatus(HttpStatus.BAD_REQUEST.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());
        problem.setTimestamp(Instant.now());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ProblemDetail> businessExceptionHandler(BusinessException ex, HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        ProblemDetail problem = new ProblemDetail();
        problem.setType(request.getRequestURL().toString());
        problem.setTitle("Erro de negócio");
        problem.setStatus(HttpStatus.CONFLICT.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());
        problem.setTimestamp(Instant.now());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<ProblemDetail> internalErrorExceptionHandler(InternalErrorException ex, HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        ProblemDetail problem = new ProblemDetail();
        problem.setType(request.getRequestURL().toString());
        problem.setTitle("Erro interno");
        problem.setStatus(HttpStatus.BAD_GATEWAY.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());
        problem.setTimestamp(Instant.now());

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ProblemDetail> notFoundExceptionHandler(NotFoundException ex, HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        ProblemDetail problem = new ProblemDetail();
        problem.setType(request.getRequestURL().toString());
        problem.setTitle("Entidade não encontrada");
        problem.setStatus(HttpStatus.NOT_FOUND.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());
        problem.setTimestamp(Instant.now());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemDetail> httpMessageNotReadableHandler(HttpMessageNotReadableException ex, HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        ProblemDetail problem = new ProblemDetail();
        problem.setType(request.getRequestURL().toString());
        problem.setTitle("Campo não reconhecido");
        problem.setStatus(HttpStatus.NOT_FOUND.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());
        problem.setTimestamp(Instant.now());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity<ProblemDetail> dataAccessResourceFailureException(DataAccessResourceFailureException ex, HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        ProblemDetail problem = new ProblemDetail();
        problem.setType(request.getRequestURL().toString());
        problem.setTitle("Erro de comumicação com o database");
        problem.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());
        problem.setTimestamp(Instant.now());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CannotCreateTransactionException.class)
    public ResponseEntity<ProblemDetail> cannotCreateTransactionException(CannotCreateTransactionException ex, HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        ProblemDetail problem = new ProblemDetail();
        problem.setType(request.getRequestURL().toString());
        problem.setTitle("Erro de comumicação com o database");
        problem.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());
        problem.setTimestamp(Instant.now());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}