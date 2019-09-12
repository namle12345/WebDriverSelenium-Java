package stepDefinitionFiles;

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
import cucumber.api.java.en.Then;

public class BuyStockStepDefinition {
	private WebDriver driver = null;

	public BuyStockStepDefinition(BaseStepDefinition baseStepDefinition) {
		this.driver = baseStepDefinition.driver;
	}

	@Given("^a user is on the Buy Stock screen of the Trading Platform$")
	public void a_user_is_on_the_Buy_Stock_screen_of_the_Trading_Platform() throws Throwable {
		driver.get(DataFileTradingPlat.homePageURL);
		LoginPage.userNameField(driver).sendKeys(DataFileTradingPlat.userUserName);
		LoginPage.passwordField(driver).sendKeys(DataFileTradingPlat.userPassword);
		LoginPage.submitButton(driver).click();
		HomePage.balanceLink(driver).click();
		CheckBalancePage.defaultAccountButton(driver).click();
		CheckBalancePage.homeLink(driver).click();
		HomePage.buyStockLink(driver).click();
	}

	@When("^a user clicks on G link on the Buy Stock page$")
	public void a_user_clicks_on_G_link_on_the_Buy_Stock_page() throws Throwable {
		BuyStockPage.gCompanies(driver).click();
	}

	@When("^a user selects an appropriate radio button for GLENCORE INTL on the Buy Stock page$")
	public void a_user_selects_an_appropriate_radio_button_for_GLENCORE_INTL_on_the_Buy_Stock_page() throws Throwable {
		BuyStockPage.radioButton(driver).click();
	}

	@When("^a user clicks on the Buy button on the Buy Stock page$")
	public void a_user_clicks_on_the_Buy_button_on_the_Buy_Stock_page() throws Throwable {
		BuyStockPage.buyButton(driver).click();
	}

	@When("^a user enters an amount of shares to buy on the Buying Shares page$")
	public void a_user_enters_an_amount_of_shares_to_buy_on_the_Buying_Shares_page() throws Throwable {
		BuyingSharesPage.amountToBuyField(driver).sendKeys(DataFileTradingPlat.sharesToBuy);
	}

	@When("^a user clicks the Calculate button on the Buying Shares page$")
	public void a_user_clicks_the_Calculate_button_on_the_Buying_Shares_page() throws Throwable {
		BuyingSharesPage.calculateButton(driver).click();
	}

	@When("^a user clicks the Confirm button$")
	public void a_user_clicks_the_Confirm_button() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(BuyingSharesPage.confirmButton(driver)));
		BuyingSharesPage.confirmButton(driver).click();
	}

	@Then("^confirm that the Company Name and Number of Shares bought is correct on the Transaction Confirmation page$")
	public void confirm_that_the_Company_Name_and_Number_of_Shares_bought_is_correct_on_the_Transaction_Confirmation_page()
			throws Throwable {
		String actualCompanyText = TransactionConfirmationPage.companyText(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.companyName, actualCompanyText);
		String actualSharesBought = TransactionConfirmationPage.totalSharesText(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.sharesToBuy, actualSharesBought);
	}

	@Then("^confirm that the Company Name, Transaction Type and Number of Shares is correct on the Transaction History page$")
	public void confirm_that_the_Company_Name_Transaction_Type_and_Number_of_Shares_is_correct_on_the_Transaction_History_page()
			throws Throwable {
		Navbar.transactionHistoryLink(driver).click();
		String actualTransactionCompanyText = TransactionHistoryPage.companyNameField(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.companyName, actualTransactionCompanyText);
		String actualTransactionType = TransactionHistoryPage.transactionTypeField(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.transactionType, actualTransactionType);
		String actualNumberOfSharesText = TransactionHistoryPage.numberOfSharesField(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.sharesToBuy, actualNumberOfSharesText);
	}
}
