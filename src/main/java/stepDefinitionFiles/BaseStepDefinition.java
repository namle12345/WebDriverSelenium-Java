package stepDefinitionFiles;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import cucumber.api.java.Before;
import utilities.DriverUtilities;
import cucumber.api.java.After;

public class BaseStepDefinition {

	protected WebDriver driver = null;

	@Before
	public void setUp() throws Exception {
		DriverUtilities myDriverUtilities = new DriverUtilities();
		driver = myDriverUtilities.getDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
