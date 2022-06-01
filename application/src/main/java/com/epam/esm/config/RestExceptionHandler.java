package com.epam.esm.config;

import com.epam.esm.dto.reponse.BaseExceptionResponse;
import com.epam.esm.exception.*;
import com.epam.esm.exception.gift_certificate.InvalidCertificateException;
import com.epam.esm.exception.tag.InvalidTagException;
import com.epam.esm.exception.user.InvalidUserException;
import org.postgresql.util.PSQLException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<BaseExceptionResponse> classNotFoundExceptionHandler(Exception e){
        return ResponseEntity.ok(
                new BaseExceptionResponse(500, e.getLocalizedMessage(), 10500)
        );
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<BaseExceptionResponse> psqlExceptionHandler(PSQLException e){
        return ResponseEntity.status(400).body(
                new BaseExceptionResponse(400, e.getLocalizedMessage(), 10500)
        );
    }

    @ExceptionHandler(DataAlreadyExistException.class)
    public ResponseEntity<BaseExceptionResponse> tagAlreadyExistExceptionHandler(DataAlreadyExistException e){
        return ResponseEntity.badRequest().body(
                new BaseExceptionResponse(400, e.getMessage(), 10400)
        );
    }

    @ExceptionHandler(InvalidTagException.class)
    public ResponseEntity<BaseExceptionResponse> invalidTagExceptionHandler(InvalidTagException e){
        return ResponseEntity.badRequest().body(
                new BaseExceptionResponse(400, e.getMessage(), 10400)
        );
    }

    @ExceptionHandler(InvalidCertificateException.class)
    public ResponseEntity<BaseExceptionResponse> invalidCertificateExceptionHandler(InvalidCertificateException e){
        return ResponseEntity.badRequest().body(
                new BaseExceptionResponse(400, e.getMessage(), 10400)
        );
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<BaseExceptionResponse> invalidUserExceptionHandler(InvalidUserException e){
        return ResponseEntity.badRequest().body(
                new BaseExceptionResponse(400, e.getMessage(), 10400)
        );
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<BaseExceptionResponse> noDataFoundExceptionHandler(NoDataFoundException e){
        return ResponseEntity.status(400).body(
                new BaseExceptionResponse(400, e.getMessage(), 10204)
        );
    }

    @ExceptionHandler(UnknownDataBaseException.class)
    public ResponseEntity<BaseExceptionResponse> unknownDatabaseExceptionHandler(UnknownDataBaseException e){
        return ResponseEntity.status(500).body(
                new BaseExceptionResponse(500, e.getMessage(), 10500)
        );
    }

    @ExceptionHandler({InvalidInputException.class})
    public ResponseEntity<BaseExceptionResponse> invalidInputException(InvalidInputException e){
        StringBuilder message = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach((error) -> message.append(error.getDefaultMessage()).append("\n"));
        return ResponseEntity.badRequest()
                .body(new BaseExceptionResponse(400, message.toString(), 10400));
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<BaseExceptionResponse> numberFormatExceptionHandler(NumberFormatException e){
        return ResponseEntity.status(400).body(
                new BaseExceptionResponse(400, e.getMessage(), 10400)
        );
    }

    @ExceptionHandler(BreakingDataRelationshipException.class)
    public ResponseEntity<BaseExceptionResponse> breakingDataRelationshipExceptionHandler(
            BreakingDataRelationshipException e
    ){
        return ResponseEntity.status(400).body(
                new BaseExceptionResponse(400, e.getMessage(), 10400)
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseExceptionResponse> constraintViolationExceptionHandler(ConstraintViolationException e){
        StringBuilder message = new StringBuilder();
        e.getConstraintViolations().forEach(violation -> message.append(violation.getMessage()).append("\n"));
        return ResponseEntity.status(400).body(
                new BaseExceptionResponse(400, message.toString(), 10400)
        );
    }

}
