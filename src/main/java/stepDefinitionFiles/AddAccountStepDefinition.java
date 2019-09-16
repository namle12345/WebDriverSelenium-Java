package stepDefinitionFiles;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pageObjectModels.AddNewAccountPage;
import pageObjectModels.CheckBalancePage;
import pageObjectModels.HomePage;
import pageObjectModels.LoginPage;
import testData.DataFileTradingPlat;
import testScripts.LoginTest;
import cucumber.api.java.en.Then;

public class AddAccountStepDefinition {
	private WebDriver driver = null;

	public AddAccountStepDefinition(BaseStepDefinition baseStepDefinition) {
		this.driver = baseStepDefinition.driver;
	}

	@Given("^a user is on the Check Balance screen of the Trading Platform$")
	public void a_user_is_on_the_Check_Balance_screen_of_the_Trading_Platform() throws Throwable {
		driver.get(DataFileTradingPlat.homePageURL);
		LoginTest.login(driver);
		HomePage.balanceLink(driver).click();
		Assert.assertEquals(DataFileTradingPlat.checkBalanceURL, driver.getCurrentUrl());
	}

	@When("^a user clicks on Add New Account link on the Check Balance page$")
	public void a_user_clicks_on_Add_New_Account_link_on_the_Check_Balance_page() throws Throwable {
		CheckBalancePage.addNewAccountLink(driver).click();
	}
	
	@Then("^confirm that the user is correctly brought to Add New Account page$")
	public void confirm_that_the_user_is_correctly_brought_to_Add_New_Account_page() throws Throwable {
		Assert.assertEquals(DataFileTradingPlat.addAccountURL, driver.getCurrentUrl());
	}

	@When("^a user selects an appropriate currency type on the Add New Account page$")
	public void a_user_selects_an_appropriate_currency_type_on_the_Add_New_Account_page() throws Throwable {
		WebElement currencyField = AddNewAccountPage.currencyDropDown(driver);
		Select currencyFieldSelect = new Select(currencyField);
		currencyFieldSelect.selectByVisibleText(DataFileTradingPlat.accountType);
	}

	@When("^a user clicks on the Add Account button on the Add New Account page$")
	public void a_user_clicks_on_the_Add_Account_button_on_the_Add_New_Account_page() throws Throwable {
		AddNewAccountPage.submitButton(driver).click();
	}
	
	@Then("^confirm that the account created is the correct currency type$")
	public void confirm_that_the_account_created_is_the_correct_currency_type() throws Throwable {
		String actualAccountCurrency = CheckBalancePage.USDCurrencyCell(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.currencyType, actualAccountCurrency);
	}

	@When("^a user clicks Add Funds on the newly made account row$")
	public void a_user_clicks_Add_Funds_on_the_newly_made_account_row() throws Throwable {
		CheckBalancePage.addFundsButton(driver).click();
	}

	@When("^a user enters the value they wish to deposit into the account$")
	public void a_user_enters_the_value_they_wish_to_deposit_into_the_account() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(CheckBalancePage.creditField(driver)));
		CheckBalancePage.creditField(driver).sendKeys(DataFileTradingPlat.fundAmount);
	}

	@When("^a user clicks the confirm button$")
	public void a_user_clicks_the_confirm_button() throws Throwable {
		CheckBalancePage.submitButton(driver).click();
	}

	@Then("^confirm that the appropriate account has the correct number of funds added to it$")
	public void confirm_that_the_appropriate_account_has_the_correct_number_of_funds_added_to_it() throws Throwable {
		String actualAccountBalance = CheckBalancePage.USDBalanceCell(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.fundAmountLong, actualAccountBalance);
	}
	
	


}
