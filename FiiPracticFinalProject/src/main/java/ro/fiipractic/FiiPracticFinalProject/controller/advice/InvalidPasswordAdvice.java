package ro.fiipractic.FiiPracticFinalProject.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.fiipractic.FiiPracticFinalProject.exception.InvalidPasswordException;

@ControllerAdvice
public class InvalidPasswordAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleInvalidPassword(InvalidPasswordException ex) {
        return ex.getMessage();
    }
}
