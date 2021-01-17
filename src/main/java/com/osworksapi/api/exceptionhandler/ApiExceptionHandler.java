package com.osworksapi.api.exceptionhandler;

import com.osworksapi.domain.exceptions.BusinessException;
import com.osworksapi.domain.exceptions.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error error = new Error(status.value(), OffsetDateTime.now(), ex.getMessage());
        return super.handleExceptionInternal(ex, error, headers, status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusiness(BusinessException e, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Error error = makeError(status.value(), e.getMessage());

        return super.handleExceptionInternal(e, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        Error error = makeError(status.value(), e.getMessage());

        return super.handleExceptionInternal(e, error, new HttpHeaders(), status, request);
    }

    private Error makeError(Integer status, String title) {
        return Error.builder()
                .status(status)
                .dataTime(OffsetDateTime.now())
                .title(title)
                .build();
    }
}
