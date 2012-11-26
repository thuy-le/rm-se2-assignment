package devfortress.models.exceptions;

/**
 *
 * @author Team Poseidon
 */
public class InvalidDevDateException extends DevFortressException {

    /**
     * Creates a new instance of <code>InvalidDevDateException</code> without detail message.
     */
    public InvalidDevDateException() {
        super("Invalid Date.");
    }

    /**
     * Constructs an instance of <code>InvalidDevDateException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidDevDateException(String msg) {
        super(msg);
    }
}
