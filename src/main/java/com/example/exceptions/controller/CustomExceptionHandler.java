package com.example.exceptions.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
/**
 * Esta clase sirve como un gestor de excepciones para los controladores en
 * general.
 *
 * Aqui se pueden administrar aquellos eventos causados por el controlador en un
 * momento dado, como lo son las excepciones detectadas a nivel controlador (500
 * Internal Server Error, 400 Bad Request, etcetera)
 */
public class CustomExceptionHandler {

    // Gestionar errores de validacion unicamente.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        // Crear una lista para los errores de validacion.
        List<String> errors = new ArrayList<>();

        // Iterar por cada uno.
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors())
            errors.add(fieldError.getDefaultMessage());

        // Crear el body original de Springboot, agregando una propiedad nueva donde
        // esten los errores presentes.
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("errors", errors);
        body.put("path", request.getDescription(false).replace("uri=", ""));

        // Retornar la respuesta.
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}