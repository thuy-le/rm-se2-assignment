/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.exceptions;

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
