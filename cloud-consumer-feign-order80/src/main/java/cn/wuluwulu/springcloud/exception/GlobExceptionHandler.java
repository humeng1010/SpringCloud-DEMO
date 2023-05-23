package cn.wuluwulu.springcloud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);

    }
}
