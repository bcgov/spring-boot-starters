package ca.bc.gov.open.clamav.starter;

public class ClamAvException extends RuntimeException {

    public ClamAvException(String message, Throwable cause) {
        super(message, cause);
    }
}
