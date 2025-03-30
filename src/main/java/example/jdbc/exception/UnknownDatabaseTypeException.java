package example.jdbc.exception;

public class UnknownDatabaseTypeException extends RuntimeException {
    public UnknownDatabaseTypeException(String message) {
        super(message);
    }
}