package devfortress.models.exceptions;

/**
 *
 * @author Team Poseidon
 */
public class InvalidFunctionalAreaException extends DevFortressException {

    public InvalidFunctionalAreaException() {
        super("Invalid Functional Area was provided");
    }

    public InvalidFunctionalAreaException(String message) {
        super(message);
    }
}
