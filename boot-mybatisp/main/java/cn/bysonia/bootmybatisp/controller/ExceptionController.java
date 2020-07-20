package cn.bysonia.bootmybatisp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 将 错误处理移到syslogInterceptor里了
 */
//@ControllerAdvice
public class ExceptionController {

    //    @ExceptionHandler(value=Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return new ResponseEntity<>("not found11111", HttpStatus.NOT_FOUND);
    }
}
