package testScripts;

import utilities.DriverUtilities;
import pageObjectModels.RegistrationConfirmedPage;
import pageObjectModels.RegistrationPage;
import pageObjectModels.HomePage;
import pageObjectModels.LoginPage;
import testData.DataFileTradingPlat;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.junit.After;
import org.junit.Assert;

public class RegisterTest {
	WebDriver driver;

	@Test
	public void registration() {
		// Initialize driver
		DriverUtilities myDriverUtilities = new DriverUtilities();
		driver = myDriverUtilities.getDriver();
		
		// Go to the target website
		driver.get(DataFileTradingPlat.homePageURL);

		// Set the implicit wait to 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Navigate to Registration Page
		LoginPage.registerLink(driver).click();

		// Enter data into all fields on registration page
		WebElement titleField = RegistrationPage.titleDropDown(driver);
		Select titleFieldSelect = new Select(titleField);
		titleFieldSelect.selectByVisibleText(DataFileTradingPlat.userTitle);
		RegistrationPage.firstNameField(driver).sendKeys(DataFileTradingPlat.userFirstName);
		RegistrationPage.lastNameField(driver).sendKeys(DataFileTradingPlat.userLastName);
		RegistrationPage.emailField(driver).sendKeys(DataFileTradingPlat.userEmail);
		RegistrationPage.usernameField(driver).sendKeys(DataFileTradingPlat.userUserName);
		RegistrationPage.pwdField(driver).sendKeys(DataFileTradingPlat.userPassword);
		RegistrationPage.confirmPwdField(driver).sendKeys(DataFileTradingPlat.userPassword);
		WebElement questionField = RegistrationPage.questionDropDown(driver);
		Select questionFieldSelect = new Select(questionField);
		questionFieldSelect.selectByVisibleText(DataFileTradingPlat.userSecurityQuestion);
		RegistrationPage.answerField(driver).sendKeys(DataFileTradingPlat.userSecurityAnswer);
		RegistrationPage.confirmAnsField(driver).sendKeys(DataFileTradingPlat.userSecurityAnswer);
		RegistrationPage.submitBtn(driver).click();

		// Check correct messages appear to confirm registration success
		String actualWelcomeMessage = RegistrationConfirmedPage.welcomeMessage(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.welcomeMessage, actualWelcomeMessage);
		String actualUserNameMessage = RegistrationConfirmedPage.userNameMessage(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.userNameMessage, actualUserNameMessage);

		// Navigate to Login page
		RegistrationConfirmedPage.loginLink(driver).click();
		
		// Assert on correct Login page
		Assert.assertEquals(DataFileTradingPlat.homePageURL, driver.getCurrentUrl());
		
		// Login with newly create information
		LoginTest.login(driver);

		// Check correct message appears to confirm to login success
		String actualLoginMessage = HomePage.loginMessage(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.loginMessage, actualLoginMessage);
	}

	@After
	public void tearDown() {
		// Close the driver regardless of the test result/outcome
		driver.quit();
	}

}
