package devfortress.models.exceptions;

/**
 *
 * @author Team Poseidon
 */
public class DevFortressNotification extends Throwable {

    public DevFortressNotification() {
        super("Unspecifed Notification");
    }

    public DevFortressNotification(String message) {
        super(message);
    }

    public DevFortressNotification(Throwable cause) {
        super(cause);
    }

    public DevFortressNotification(String message, Throwable cause) {
        super(message, cause);
    }
}
