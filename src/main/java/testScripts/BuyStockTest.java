package testScripts;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjectModels.BuyStockPage;
import pageObjectModels.BuyingSharesPage;
import pageObjectModels.CheckBalancePage;
import pageObjectModels.HomePage;
import pageObjectModels.LoginPage;
import pageObjectModels.Navbar;
import pageObjectModels.ResetPasswordConfirmationPage;
import pageObjectModels.TransactionConfirmationPage;
import pageObjectModels.TransactionHistoryPage;
import testData.DataFileTradingPlat;
import utilities.DriverUtilities;

public class BuyStockTest {
	WebDriver driver;
	
	@Before
	public void startUp() {
		// Initialize driver
		DriverUtilities myDriverUtilities = new DriverUtilities();
		driver = myDriverUtilities.getDriver();
		
		// Go to the target website
		driver.get(DataFileTradingPlat.homePageURL);
		
		LoginTest.login(driver);
		
		/* Enter login details and login
		LoginPage.userNameField(driver).sendKeys(DataFileTradingPlat.userUserName);
		LoginPage.passwordField(driver).sendKeys(DataFileTradingPlat.userPassword);
		LoginPage.submitButton(driver).click();*/

		// Navigate to Check Balance screen
		HomePage.balanceLink(driver).click();

		// Assert if correctly on Check Balance page
		Assert.assertEquals(DataFileTradingPlat.checkBalanceURL, driver.getCurrentUrl());
		
		// Change Default Account to USD Account
		CheckBalancePage.defaultAccountButton(driver).click();

		// Navigate back to Buy Stock page
		CheckBalancePage.homeLink(driver).click();
		HomePage.buyStockLink(driver).click();
		
		// Assert if correctly on Buy Stock page
		Assert.assertEquals(DataFileTradingPlat.buyStockURL, driver.getCurrentUrl());
	}
	
	@Test
	public void buyStock() {
		// Buy Stock from Companies starting with G
		BuyStockPage.gCompanies(driver).click();

		// Check all companies start with G
		List <WebElement> rowsInTable = BuyStockPage.companiesTable(driver).findElements(By.tagName("tr"));
		rowsInTable.remove(0);
		for (WebElement row: rowsInTable)
		{
			System.out.println(row.getText());
			String actualBeginningLetter = row.getText().substring(0, 1);
			System.out.println(actualBeginningLetter);
			Assert.assertEquals(DataFileTradingPlat.beginningLetter, actualBeginningLetter);
		}
		
		// Assert buttons are enabled & clickable
		Assert.assertTrue(BuyStockPage.radioButton(driver).isDisplayed());
		Assert.assertTrue(BuyStockPage.radioButton(driver).isEnabled());
		
		// Ensure radio button is clicked and selected
		BuyStockPage.radioButton(driver).click();
		Assert.assertTrue(BuyStockPage.radioButton(driver).isSelected());
		
		// Navigate to the Buy Share page
		BuyStockPage.buyButton(driver).click();
		Assert.assertEquals(DataFileTradingPlat.buyingSharesTitle, driver.getTitle());
		
		// Calculate share pricing
		BuyingSharesPage.amountToBuyField(driver).sendKeys(DataFileTradingPlat.sharesToBuy);
		BuyingSharesPage.calculateButton(driver).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		
		// Assert the correct result is shown after calculating
		
		wait.until(ExpectedConditions.visibilityOf(BuyingSharesPage.transactionPriceField(driver)));
		String newTransactionFieldPrice = BuyingSharesPage.transactionPriceField(driver).getText();
		System.out.println(newTransactionFieldPrice);
		String[] splitTransactionPrice = newTransactionFieldPrice.split("\\s+");
		String transactionPrice = splitTransactionPrice[1];
		System.out.println(transactionPrice);
		int sharesToBuyInt = Integer.parseInt(DataFileTradingPlat.sharesToBuy);
		String newCostPerShareField = BuyingSharesPage.costPerShareField(driver).getText();
		System.out.println(newCostPerShareField);
		String[] splitCostPerShareField = newCostPerShareField.split("\\s+");
		String costPerShareStr = splitCostPerShareField[1];
		System.out.println(costPerShareStr);
		double costPerShareDouble = Double.parseDouble(costPerShareStr);
		double estimateTransactionPriceDouble = costPerShareDouble * sharesToBuyInt;
		String estimateTransactionPriceStr = String.valueOf(estimateTransactionPriceDouble);
		String balanceAfterTransactionField = BuyingSharesPage.balanceAfterTransaction(driver).getText();
		System.out.println(balanceAfterTransactionField);
		String[] splitbalanceAfterTransaction = balanceAfterTransactionField.split("\\s+");
		String actualBalanceAfterTransaction = splitbalanceAfterTransaction[1];
		String currentAccBalanceStr = BuyingSharesPage.currentAccountBalance(driver).getText();
		System.out.println(currentAccBalanceStr);
		String[] splitAccBalanceStr = currentAccBalanceStr.split("\\s+");
		String accBalanceStr = splitAccBalanceStr[1];
		double accBalanceDouble = Double.parseDouble(accBalanceStr);
		double estimateBalanceAfterTransaction = accBalanceDouble - estimateTransactionPriceDouble;
		String estimateBalanceAfterTransactionStr = String.valueOf(estimateBalanceAfterTransaction);
		System.out.println(estimateBalanceAfterTransactionStr);

		Assert.assertEquals(estimateTransactionPriceStr, transactionPrice);
		Assert.assertEquals(DataFileTradingPlat.sharesToBuy, BuyingSharesPage.amountToBuySummary(driver).getText());
		Assert.assertEquals(estimateBalanceAfterTransactionStr, actualBalanceAfterTransaction);
		
		// Confirm purchasing of shares
		wait.until(ExpectedConditions.elementToBeClickable(BuyingSharesPage.confirmButton(driver)));
		BuyingSharesPage.confirmButton(driver).click();

		// Assert all details are correct
		String actualCompanyText = TransactionConfirmationPage.companyText(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.companyName, actualCompanyText);
		String actualTotalPrice = TransactionConfirmationPage.totalPriceField(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.expectedTotalPrice, actualTotalPrice);
		String actualSharesBought = TransactionConfirmationPage.totalSharesText(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.sharesToBuy, actualSharesBought);
		String actualPricePerShare = TransactionConfirmationPage.pricePerShareField(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.expectedPricePerShare, actualPricePerShare);

		// Navigate to Transaction History page
		Navbar.transactionHistoryLink(driver).click();
		Assert.assertEquals(DataFileTradingPlat.transactionHistoryURL, driver.getCurrentUrl());

		// Assert all details correct, comparatively; Company Name, transaction type, no. of shares
		// and Date
		String actualTransactionCompanyText = TransactionHistoryPage.companyNameField(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.companyName, actualTransactionCompanyText);
		String actualTransactionType = TransactionHistoryPage.transactionTypeField(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.transactionType, actualTransactionType);
		String actualNumberOfSharesText = TransactionHistoryPage.numberOfSharesField(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.sharesToBuy, actualNumberOfSharesText);
		String actualDateAndTimeField = TransactionHistoryPage.dateAndTimeField(driver).getText();
		System.out.println(actualDateAndTimeField);
		String[] splitDateAndTimeField = actualDateAndTimeField.split("\\s+");
		String dateOnly = splitDateAndTimeField[0];
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Assert.assertEquals(formatter.format(date), dateOnly);
	}

	@After
	public void tearDown() {
		// Close the driver regardless of the test result/outcome
		driver.quit();
	}
}
