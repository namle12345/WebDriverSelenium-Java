package testScripts;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import pageObjectModels.HomePage;
import pageObjectModels.LoginPage;
import pageObjectModels.ResetPasswordConfirmationPage;
import pageObjectModels.ResetPasswordPage;
import testData.DataFileTradingPlat;
import utilities.DriverUtilities;

public class ResetPasswordTest {
	WebDriver driver;

	@Before
	public void startUp() {
		// Initialize driver
		DriverUtilities myDriverUtilities = new DriverUtilities();
		driver = myDriverUtilities.getDriver();
		
		// Go to the target website
		driver.get(DataFileTradingPlat.homePageURL);

		// Set the implicit wait to 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Navigate to Reset Password page
		LoginPage.resetPasswordLink(driver).click();
		
		// Assert if correctly on Reset Password page
		Assert.assertEquals(DataFileTradingPlat.resetPasswordURL, driver.getCurrentUrl());
	}
	
	@Test
	public void resetPassword() {
		// Enter in correct details
		ResetPasswordPage.usernameField(driver).sendKeys(DataFileTradingPlat.userUserName);
		WebElement questionField = ResetPasswordPage.questionDropDown(driver);
		Select questionFieldSelect = new Select(questionField);
		questionFieldSelect.selectByVisibleText(DataFileTradingPlat.userSecurityQuestion);
		ResetPasswordPage.answerField(driver).sendKeys(DataFileTradingPlat.userSecurityAnswer);
		ResetPasswordPage.resetPasswordButton(driver).click();

		// Check correct reset confirmation message appears
		String actualResetMessage = ResetPasswordConfirmationPage.resetConfirmMessage(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.resetConfirmationMessage, actualResetMessage);

		// Grab new password & navigate to Login page
		String newPasswordLine = ResetPasswordConfirmationPage.newPasswordField(driver).getText();
		newPasswordLine = newPasswordLine.replaceAll("\\s+", "");
		String[] splitPasswordLine = newPasswordLine.split(":", 2);
		String tmpPassword = splitPasswordLine[1];
		ResetPasswordConfirmationPage.homeLink(driver).click();

		// Assert if correctly navigated to homepage
		Assert.assertEquals(DataFileTradingPlat.homePageURL, driver.getCurrentUrl()); 
		
		// Login with new password details
		LoginPage.userNameField(driver).sendKeys(DataFileTradingPlat.userUserName);
		LoginPage.passwordField(driver).sendKeys(tmpPassword);
		LoginPage.submitButton(driver).click();

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
