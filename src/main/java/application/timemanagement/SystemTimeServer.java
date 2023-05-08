package application.timemanagement;

import java.time.LocalDate;

/**
 * @author Mads Christian Wrang Nielsen - s224784
 */
public class SystemTimeServer implements TimeServer {
    public int getYear() {
        return LocalDate.now().getYear();
    }
}
