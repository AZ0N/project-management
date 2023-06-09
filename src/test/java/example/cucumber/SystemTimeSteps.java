package example.cucumber;

import application.timemanagement.ManualTimeServer;
import io.cucumber.java.en.Given;

/**
 * @author Mads Christian Wrang Nielsen - s224784
 */
public class SystemTimeSteps {
	
	private ManualTimeServer manualTimeServer;

	public SystemTimeSteps(ManualTimeServer manualTimeServer) {
		this.manualTimeServer = manualTimeServer;
	}
	
	@Given("the year is {int}")
	public void theYearIs(int year) {
		manualTimeServer.setYear(year);
	}
}
