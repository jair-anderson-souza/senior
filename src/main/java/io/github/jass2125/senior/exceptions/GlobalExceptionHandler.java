package io.github.jass2125.senior.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        GlobalException apiError = GlobalException
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .mensagem(ex.getLocalizedMessage())
                .erros(errors)
                .build();
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = "Request body is missing";
        GlobalException apiError = GlobalException
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .mensagem(ex.getLocalizedMessage())
                .erros(Arrays.asList(message)).build();
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());

    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = "The content-type is not supported";
        GlobalException apiError = GlobalException
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .mensagem(ex.getLocalizedMessage())
                .erros(Arrays.asList(message)).build();
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
        GlobalException apiError = GlobalException
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .mensagem(ex.getLocalizedMessage())
                .erros(Arrays.asList(builder.toString())).build();

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

        GlobalException apiError = GlobalException
                .builder()
                .status(HttpStatus.NOT_FOUND)
                .mensagem(ex.getLocalizedMessage())
                .erros(Arrays.asList(error)).build();

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                   WebRequest request) {
        String error = "Example -> 00.00";
        String message = "The parameter is invalid";
        GlobalException apiError = GlobalException
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .mensagem(ex.getLocalizedMessage())
                .erros(Arrays.asList(error)).build();
        return ResponseEntity.ok().body(apiError);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
                    + violation.getMessage());
        }

        GlobalException apiError = GlobalException
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .mensagem(ex.getLocalizedMessage())
                .erros(errors).build();
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ UrlAlreadyExists.class })
    public ResponseEntity<Object> handleUrlAlreadyExists(UrlAlreadyExists ex, WebRequest request) {

        List<String> errors = Arrays.asList(ex.getMessage());

        GlobalException apiError = new GlobalException(HttpStatus.BAD_REQUEST, ex.getMessage(), errors);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        GlobalException apiError = GlobalException
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .mensagem(ex.getLocalizedMessage())
                .erros(Arrays.asList(error)).build();
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}