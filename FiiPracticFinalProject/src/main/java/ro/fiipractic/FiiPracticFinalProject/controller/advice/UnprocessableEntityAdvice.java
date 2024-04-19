package ro.fiipractic.FiiPracticFinalProject.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.fiipractic.FiiPracticFinalProject.exception.UnprocessableEntityException;

@ControllerAdvice
public class UnprocessableEntityAdvice {
    @ResponseBody
    @ExceptionHandler(UnprocessableEntityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleUnprocessableEntity(UnprocessableEntityException ex) {
        return "{ \"error\" : \"" + ex.getMessage() + "\" }";
    }
}
