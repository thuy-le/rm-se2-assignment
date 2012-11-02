/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.exceptions;

/**
 *
 * @author Team Poseidon
 */
public class GameNotInitilizedException extends Exception {

    public GameNotInitilizedException(String message) {
        super(message);
    }

    public GameNotInitilizedException() {
        super("The game engine has not been initialized");
    }
}
