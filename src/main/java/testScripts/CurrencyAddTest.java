package testScripts;

import utilities.DriverUtilities;
import pageObjectModels.AddNewAccountPage;
import pageObjectModels.CheckBalancePage;
import pageObjectModels.HomePage;
import pageObjectModels.LoginPage;
import testData.DataFileTradingPlat;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.After;
import org.junit.Assert;

public class CurrencyAddTest {
	// Create instance of WebDriver
	DriverUtilities myDriverUtilities = new DriverUtilities();
	WebDriver driver = myDriverUtilities.getDriver();

	@Test
	public void addCurrency() {
		// Go to the target website
		driver.get(DataFileTradingPlat.homePageURL);

		// Enter login details and login
		LoginPage.userNameField(driver).sendKeys(DataFileTradingPlat.userUserName);
		LoginPage.passwordField(driver).sendKeys(DataFileTradingPlat.userPassword);
		LoginPage.submitButton(driver).click();

		// Navigate to Check Balance screen
		HomePage.balanceLink(driver).click();

		// Add new Currency Account
		CheckBalancePage.addNewAccountLink(driver).click();
		WebElement currencyField = AddNewAccountPage.currencyDropDown(driver);
		Select currencyFieldSelect = new Select(currencyField);
		currencyFieldSelect.selectByVisibleText(DataFileTradingPlat.accountType);
		AddNewAccountPage.submitButton(driver).click();

		// Assert US Account has been created
		String actualAccountCurrency = CheckBalancePage.USDCurrencyCell(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.currencyType, actualAccountCurrency);

		// Add funds to US account
		CheckBalancePage.addFundsButton(driver).click();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(CheckBalancePage.creditField(driver)));
		CheckBalancePage.creditField(driver).sendKeys(DataFileTradingPlat.fundAmount);
		CheckBalancePage.submitButton(driver).click();

		// Assert 50000 has been added
		String actualAccountBalance = CheckBalancePage.USDBalanceCell(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.fundAmountLong, actualAccountBalance);

		// Log off to ensure no cookies / cache login issue for other tests
		HomePage.logoutLink(driver).click();
	}

	@After
	public void tearDown() {
		// Close the driver regardless of the test result/outcome
		driver.quit();
	}
}
