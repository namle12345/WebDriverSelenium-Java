package testScripts;

import org.openqa.selenium.WebDriver;

import pageObjectModels.LoginPage;
import testData.DataFileTradingPlat;

public class LoginTest {
	public static void login(WebDriver driver) {
	// Enter login details and login
		LoginPage.userNameField(driver).sendKeys(DataFileTradingPlat.userUserName);
		LoginPage.passwordField(driver).sendKeys(DataFileTradingPlat.userPassword);
		LoginPage.submitButton(driver).click();
	}
}
