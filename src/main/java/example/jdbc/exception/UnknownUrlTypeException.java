package example.jdbc.exception;

public class UnknownUrlTypeException extends RuntimeException {
    public UnknownUrlTypeException(String message) {
        super(message);
    }
}