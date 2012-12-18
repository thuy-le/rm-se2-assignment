package devfortress.models.exceptions;

/**
 *
 * @author Team Poseidon
 */
public class InvalidDevDateException extends DevFortressException {

    public InvalidDevDateException() {
        super("Invalid Date.");
    }
    
    public InvalidDevDateException(String msg) {
        super(msg);
    }
}
