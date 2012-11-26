/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models.exceptions;

/**
 *
 * @author Team Poseidon
 */
public class GameAlreadyInitializedException extends DevFortressException {

    public GameAlreadyInitializedException(String message) {
        super(message);
    }

    public GameAlreadyInitializedException() {
        super("The game engine has already been initialized");
    }
}
