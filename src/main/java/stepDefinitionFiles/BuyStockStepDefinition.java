package stepDefinitionFiles;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pageObjectModels.BuyStockPage;
import pageObjectModels.BuyingSharesPage;
import pageObjectModels.CheckBalancePage;
import pageObjectModels.HomePage;
import pageObjectModels.LoginPage;
import pageObjectModels.Navbar;
import pageObjectModels.TransactionConfirmationPage;
import pageObjectModels.TransactionHistoryPage;
import testData.DataFileTradingPlat;
import testScripts.LoginTest;
import cucumber.api.java.en.Then;

public class BuyStockStepDefinition {
	private WebDriver driver = null;
	WebDriverWait wait = new WebDriverWait(driver, 60);

	public BuyStockStepDefinition(BaseStepDefinition baseStepDefinition) {
		this.driver = baseStepDefinition.driver;
	}

	@Given("^a user is on the Buy Stock screen of the Trading Platform$")
	public void a_user_is_on_the_Buy_Stock_screen_of_the_Trading_Platform() throws Throwable {
		driver.get(DataFileTradingPlat.homePageURL);
		LoginTest.login(driver);
		HomePage.balanceLink(driver).click();
		Assert.assertEquals(DataFileTradingPlat.checkBalanceURL, driver.getCurrentUrl());
		CheckBalancePage.defaultAccountButton(driver).click();
		CheckBalancePage.homeLink(driver).click();
		HomePage.buyStockLink(driver).click();
		Assert.assertEquals(DataFileTradingPlat.buyStockURL, driver.getCurrentUrl());
	}

	@When("^a user clicks on G link on the Buy Stock page$")
	public void a_user_clicks_on_G_link_on_the_Buy_Stock_page() throws Throwable {
		BuyStockPage.gCompanies(driver).click();
	}
	
	@Then("^confirm radio button is enabled and is clickable for the user$")
	public void confirm_radio_button_is_enabled_and_is_clickable_for_the_user() throws Throwable {
		Assert.assertTrue(BuyStockPage.radioButton(driver).isDisplayed());
		Assert.assertTrue(BuyStockPage.radioButton(driver).isEnabled());
	}

	@When("^a user selects an appropriate radio button for GLENCORE INTL on the Buy Stock page$")
	public void a_user_selects_an_appropriate_radio_button_for_GLENCORE_INTL_on_the_Buy_Stock_page() throws Throwable {
		BuyStockPage.radioButton(driver).click();
	}
	
	@Then("^confirm the radio button clicked has been properly selected$")
	public void confirm_the_radio_button_clicked_has_been_properly_selected() throws Throwable {
		Assert.assertTrue(BuyStockPage.radioButton(driver).isSelected());
	}

	@When("^a user clicks on the Buy button on the Buy Stock page$")
	public void a_user_clicks_on_the_Buy_button_on_the_Buy_Stock_page() throws Throwable {
		BuyStockPage.buyButton(driver).click();
	}
	
	@Then("^confirm that the user is correctly brought to Buy Shares page$")
	public void confirm_that_the_user_is_correctly_brought_to_Buy_Shares_page() throws Throwable {
		Assert.assertEquals(DataFileTradingPlat.buyingSharesTitle, driver.getTitle());
	}

	@When("^a user enters an amount of shares to buy on the Buying Shares page$")
	public void a_user_enters_an_amount_of_shares_to_buy_on_the_Buying_Shares_page() throws Throwable {
		BuyingSharesPage.amountToBuyField(driver).sendKeys(DataFileTradingPlat.sharesToBuy);
	}

	@When("^a user clicks the Calculate button on the Buying Shares page$")
	public void a_user_clicks_the_Calculate_button_on_the_Buying_Shares_page() throws Throwable {
		BuyingSharesPage.calculateButton(driver).click();
	}
	
	@Then("^confirm that the calculate results shown in Transaction Summary are correct$")
	public void confirm_that_the_calculate_results_shown_in_Transaction_Summary_are_correct() throws Throwable {
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
	}

	@When("^a user clicks the Confirm button$")
	public void a_user_clicks_the_Confirm_button() throws Throwable {
		wait.until(ExpectedConditions.elementToBeClickable(BuyingSharesPage.confirmButton(driver)));
		BuyingSharesPage.confirmButton(driver).click();
	}

	@Then("^confirm that all the details are correct on the Transaction Confirmation page$")
	public void confirm_that_all_the_details_are_correct_on_the_Transaction_Confirmation_page() throws Throwable {
		String actualCompanyText = TransactionConfirmationPage.companyText(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.companyName, actualCompanyText);
		String actualTotalPrice = TransactionConfirmationPage.totalPriceField(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.expectedTotalPrice, actualTotalPrice);
		String actualSharesBought = TransactionConfirmationPage.totalSharesText(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.sharesToBuy, actualSharesBought);
		String actualPricePerShare = TransactionConfirmationPage.pricePerShareField(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.expectedPricePerShare, actualPricePerShare);

	}

	@When("^a user clicks the Transaction History link on the Navbar$")
	public void a_user_clicks_the_Transaction_History_link_on_the_Navbar() throws Throwable {
		Navbar.transactionHistoryLink(driver).click();
	}

	@Then("^confirm that the user is correctly brought to Transaction History page$")
	public void confirm_that_the_user_is_correctly_brought_to_Transaction_History_page() throws Throwable {
		Assert.assertEquals(DataFileTradingPlat.transactionHistoryURL, driver.getCurrentUrl());
	}

	@Then("^confirm all details are correct on the Transaction History page$")
	public void confirm_all_details_are_correct_on_the_Transaction_History_page() throws Throwable {
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
	


	

	


	
}
