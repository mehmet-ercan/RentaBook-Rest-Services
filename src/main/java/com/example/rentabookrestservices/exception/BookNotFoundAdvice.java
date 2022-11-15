package com.example.rentabookrestservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class BookNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    String bookNotFoundHandler(BookNotFoundException ex) {
        return ex.getMessage();
    }

}
