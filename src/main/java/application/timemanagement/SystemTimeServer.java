package application.timemanagement;

import java.time.LocalDate;

public class SystemTimeServer implements TimeServer {
    public int getYear() {
        return LocalDate.now().getYear();
    }
}
