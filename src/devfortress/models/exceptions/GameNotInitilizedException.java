package devfortress.models.exceptions;

/**
 *
 * @author Team Poseidon
 */
public class GameNotInitilizedException extends DevFortressException {

    public GameNotInitilizedException(String message) {
        super(message);
    }

    public GameNotInitilizedException() {
        super("The game engine has not been initialized");
    }
}
