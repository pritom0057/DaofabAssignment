package com.assignment.handler;

import com.assignment.exceptions.DaoFabServiceExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@Slf4j
public class DaoFabServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {Exception.class})
    protected ResponseEntity<Object> handleUncaughtExeption(
            Exception ex, WebRequest request) {
        log.error("Exception occurred ", ex.getMessage());
        var bodyOfResponse = "Some Thing Went Wrong";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(new DaoFabServiceExceptionResponse("500", bodyOfResponse));
    }

}