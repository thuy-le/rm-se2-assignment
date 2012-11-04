/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.exceptions;

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
