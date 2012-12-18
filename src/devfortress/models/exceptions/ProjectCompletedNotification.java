package devfortress.models.exceptions;

/**
 *
 * @author Tang Vinh Thanh <s3245715@rmit.edu.vn>
 */
public class ProjectCompletedNotification extends DevFortressNotification {

    public ProjectCompletedNotification() {
        super("There are some project had been completed resently.");
    }

    public ProjectCompletedNotification(String message) {
        super(message);
    }
}
