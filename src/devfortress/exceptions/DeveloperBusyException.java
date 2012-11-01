/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.exceptions;

/**
 *
 * @author Team Poseidon
 */
public class DeveloperBusyException extends Exception {

    public DeveloperBusyException(String message) {
        super(message);
    }

    public DeveloperBusyException() {
        super("Developer is working on another project");
    }
}
