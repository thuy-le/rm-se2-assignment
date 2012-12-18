package devfortress.models.exceptions;

/**
 *
 * @author Team Poseidon
 */
public class InsufficientBudgetException extends DevFortressException {

    public InsufficientBudgetException() {
        super("Budget is insufficient");
    }

    public InsufficientBudgetException(String message) {
        super(message);
    }
}
