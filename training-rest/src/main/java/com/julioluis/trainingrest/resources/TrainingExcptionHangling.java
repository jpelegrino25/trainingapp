package com.julioluis.trainingrest.resources;

import com.julioluis.trainingrest.dto.ExceptionResponseDTO;
import com.julioluis.trainingrest.utils.UserException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class TrainingExcptionHangling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public  ResponseEntity<Object> handleAllTrainingException(Exception ex, WebRequest request) {

        ExceptionResponseDTO responseDTO=new ExceptionResponseDTO(new Date(),
                ex.getMessage(),request.getDescription(false));

        return new  ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(UserException.class)
    public  ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {

        ExceptionResponseDTO responseDTO=new ExceptionResponseDTO(new Date(),
                ex.getMessage(),request.getDescription(false));

        return new  ResponseEntity(responseDTO, HttpStatus.NOT_FOUND);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponseDTO responseDTO=new ExceptionResponseDTO(new Date(),
                "Validation Failed",ex.getBindingResult().toString());

        return new  ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
