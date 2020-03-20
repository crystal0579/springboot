package cn.bysonia.bootmybatisp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value=Exception.class)
    public ResponseEntity<Object> handleException(Exception e){
        return new ResponseEntity<>("not found11111", HttpStatus.NOT_FOUND);
    }
}
