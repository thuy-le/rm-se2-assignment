package devfortress.models.exceptions;

/**
 *
 * @author Team Poseidon
 */
public class GameOverException extends Exception {

    public GameOverException() {
        super("You have lost all your money. You lose");
    }

    public GameOverException(String message) {
        super(message);
    }
}
