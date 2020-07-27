package exceptions;

public class UsedCarServiceException extends RuntimeException {
    public UsedCarServiceException(String message) {
        super(message);
    }

    public UsedCarServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
