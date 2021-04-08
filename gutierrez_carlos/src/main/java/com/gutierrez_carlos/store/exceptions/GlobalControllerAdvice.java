package com.gutierrez_carlos.store.exceptions;
import com.gutierrez_carlos.store.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalControllerAdvice {
    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity articleNotFoundHandler(ArticleNotFoundException e){
        return new ResponseEntity(new ErrorDTO(e.getMessage()), e.getStatus());
    }

    @ExceptionHandler(DataLoadException.class)
    public ResponseEntity dataLoadErrorHandler(ArticleNotFoundException e){
        return new ResponseEntity(new ErrorDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FilterErrorException.class)
    public ResponseEntity filteringErrorHandler(FilterErrorException e){
        return new ResponseEntity(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity numberExceptionHandler(){
        return new ResponseEntity(new ErrorDTO("Error, formato de numero invalido"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsuficientStockException.class)
    public ResponseEntity notEnoughStock(InsuficientStockException e){
        return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity otherError(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO("Error interno del servidor tenemos problemas para procesar su solicitud"));
    }


}
