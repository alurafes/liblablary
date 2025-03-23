package rocks.alurafes.liblablary.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import rocks.alurafes.liblablary.model.BasicResponse;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(NoSuchElementException.class)
    public BasicResponse<String> handleNoSuchElementException(NoSuchElementException ex) {
        return new BasicResponse<>("entity not found");
    }
}
