package ro.fiipractic.FiiPracticFinalProject.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.fiipractic.FiiPracticFinalProject.exception.UserNotFoundException;


@ControllerAdvice
public class UserNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String employeeNotFoundHandler(UserNotFoundException ex) {
        return "{ \"error\" : \"" + ex.getMessage() + "\" }";
    }
}