package ro.fiipractic.FiiPracticFinalProject.exception;

public class WeakPasswordException extends  RuntimeException {
    public WeakPasswordException(String message){
        super(message);
    }
}
