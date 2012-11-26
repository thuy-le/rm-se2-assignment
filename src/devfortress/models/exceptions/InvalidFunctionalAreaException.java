/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
