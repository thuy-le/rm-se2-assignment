package devfortress.models.exceptions;

/**
 *
 * @author Team Poseidon
 */
public class DeveloperBusyException extends DevFortressException {

    public DeveloperBusyException(String message) {
        super(message);
    }

    public DeveloperBusyException() {
        super("Developer is working on a project");
    }
}
