package testScripts;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjectModels.BuyStockPage;
import pageObjectModels.BuyingSharesPage;
import pageObjectModels.CheckBalancePage;
import pageObjectModels.HomePage;
import pageObjectModels.LoginPage;
import pageObjectModels.Navbar;
import pageObjectModels.TransactionConfirmationPage;
import pageObjectModels.TransactionHistoryPage;
import testData.DataFileTradingPlat;
import utilities.DriverUtilities;

public class BuyStockTest {
	// Create instance of WebDriver
	DriverUtilities myDriverUtilities = new DriverUtilities();
	WebDriver driver = myDriverUtilities.getDriver();

	@Test
	public void buyStock() {
		// Go to the target website
		driver.get(DataFileTradingPlat.homePageURL);

		// Enter login details and login
		LoginPage.userNameField(driver).sendKeys(DataFileTradingPlat.userUserName);
		LoginPage.passwordField(driver).sendKeys(DataFileTradingPlat.userPassword);
		LoginPage.submitButton(driver).click();

		// Navigate to Check Balance screen
		HomePage.balanceLink(driver).click();

		// Change Default Account to USD Account
		CheckBalancePage.defaultAccountButton(driver).click();

		// Navigate back to Buy Stock page
		CheckBalancePage.homeLink(driver).click();
		HomePage.buyStockLink(driver).click();

		// Buy Stock from Companies starting with G
		BuyStockPage.gCompanies(driver).click();

		// Assert buttons are enabled & clickable
		Assert.assertTrue(BuyStockPage.radioButton(driver).isDisplayed());
		Assert.assertTrue(BuyStockPage.radioButton(driver).isEnabled());

		// Calculate and Buy Stock from Company
		BuyStockPage.radioButton(driver).click();
		BuyStockPage.buyButton(driver).click();
		BuyingSharesPage.amountToBuyField(driver).sendKeys(DataFileTradingPlat.sharesToBuy);
		BuyingSharesPage.calculateButton(driver).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(BuyingSharesPage.confirmButton(driver)));
		BuyingSharesPage.confirmButton(driver).click();

		// Assert Company text and Share number bought are correct
		String actualCompanyText = TransactionConfirmationPage.companyText(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.companyName, actualCompanyText);
		String actualSharesBought = TransactionConfirmationPage.totalSharesText(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.sharesToBuy, actualSharesBought);

		// Navigate to Transaction History page
		Navbar.transactionHistoryLink(driver).click();

		// Assert all details correct, comparatively; Company Name, transaction
		// type and no. of shares
		String actualTransactionCompanyText = TransactionHistoryPage.companyNameField(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.companyName, actualTransactionCompanyText);
		String actualTransactionType = TransactionHistoryPage.transactionTypeField(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.transactionType, actualTransactionType);
		String actualNumberOfSharesText = TransactionHistoryPage.numberOfSharesField(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.sharesToBuy, actualNumberOfSharesText);

	}

	@After
	public void tearDown() {
		// Close the driver regardless of the test result/outcome
		driver.quit();
	}
}
