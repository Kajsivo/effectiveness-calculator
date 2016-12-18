package com.kajs.api;

import com.kajs.api.error.WrongCellTypeException;
import com.kajs.api.error.WrongExtensionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({WrongExtensionException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleWrongExtensionException(WrongExtensionException e) {
    }

    @ExceptionHandler({WrongCellTypeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleWrongCellTypeException(WrongCellTypeException e) {
    }
}
