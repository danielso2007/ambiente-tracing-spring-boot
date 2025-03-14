package br.com.cursos.handlers;

import br.com.cursos.dto.LogsErrorDto;
import br.com.cursos.exceptions.BusinessException;
import br.com.cursos.exceptions.InternalErrorException;
import br.com.cursos.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LogsHandlers {

    @InitBinder("businessException")
    protected void initBusinessExceptionBinder(WebDataBinder binder) {
        binder.setAllowedFields("detailMessage");
    }

    @InitBinder("internalErrorException")
    protected void initInternalErrorExceptionBinder(WebDataBinder binder) {
        binder.setAllowedFields("detailMessage");
    }

    @InitBinder("notFoundException")
    protected void notFoundExceptionBinder(WebDataBinder binder) {
        binder.setAllowedFields("detailMessage");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public LogsErrorDto methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return new LogsErrorDto(result);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public LogsErrorDto businessExceptionHandler(BusinessException businessException) {
        return new LogsErrorDto(businessException);
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(InternalErrorException.class)
    public LogsErrorDto internalErrorExceptionHandler(InternalErrorException internalErrorException) {
        return new LogsErrorDto(internalErrorException);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public LogsErrorDto notFoundExceptionHandler(NotFoundException notFoundException) {
        return new LogsErrorDto(notFoundException);
    }
}