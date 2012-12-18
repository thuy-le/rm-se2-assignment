package devfortress.models.exceptions;

/**
 *
 * @author Team Poseidon
 */
public class DevFortressException extends Exception {

    public DevFortressException(String message) {
        super(message);
    }

    public DevFortressException() {
        super("Unspecified exception has been thrown");
    }
}
