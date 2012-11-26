/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models.exceptions;

/**
 *
 * @author Team Poseidon
 */
public class GameOverException extends Exception {

    public GameOverException() {
        super("You have lost all your money. You lose");
    }
}
