package example.cucumber;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Then;

public class SystemErrorSteps {
	
	private ErrorMessageHolder errorMessageHolder;
	
	public SystemErrorSteps(ErrorMessageHolder errorMessageHolder) {
		this.errorMessageHolder = errorMessageHolder;
	}

	@Then("the system provides the error message {string}")
	public void theSystemProvidesTheErrorMessage(String expectedErrorMessage) {
		assertEquals(expectedErrorMessage, errorMessageHolder.getErrorMessage());
	}
}
