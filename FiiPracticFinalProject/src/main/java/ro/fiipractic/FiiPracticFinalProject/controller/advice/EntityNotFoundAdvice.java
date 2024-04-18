package ro.fiipractic.FiiPracticFinalProject.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.fiipractic.FiiPracticFinalProject.exception.EntityNotFoundException;

@ControllerAdvice
public class EntityNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEntityNotFound(EntityNotFoundException ex) {
        return "{ \"error\" : \"" + ex.getMessage() + "\" }";
    }
}
