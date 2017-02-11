package com.kajs.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler({InvalidPeriodException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleInvalidPeriodException(InvalidPeriodException exception) {
    }

    @ExceptionHandler({WrongExtensionException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleWrongExtensionException(WrongExtensionException e) {
    }

    @ExceptionHandler({WrongCellTypeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleWrongCellTypeException(WrongCellTypeException e) {
    }
}
