package stepDefinitionFiles;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pageObjectModels.HomePage;
import pageObjectModels.LoginPage;
import pageObjectModels.RegistrationConfirmedPage;
import pageObjectModels.RegistrationPage;
import testData.DataFileTradingPlat;
import cucumber.api.java.en.Then;

public class RegisterStepDefinition {
	private WebDriver driver = null;
	private String paraUsername = null;
	private String paraPassword = null;
	
	public RegisterStepDefinition(BaseStepDefinition baseStepDefinition) {
		this.driver = baseStepDefinition.driver;
	}

	@Given("^a user is on the Registration page of the Trading Platform$")
	public void a_user_is_on_the_Registration_page_of_the_Trading_Platform() throws Throwable {
		driver.get(DataFileTradingPlat.homePageURL);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		LoginPage.registerLink(driver).click();
	}

	@When("^a user enters valid customer information onto the Registration page$")
	public void a_user_enters_valid_customer_data_onto_the_Registration_page() throws Throwable {
		WebElement titleField = RegistrationPage.titleDropDown(driver);
		Select titleFieldSelect = new Select(titleField);
		titleFieldSelect.selectByVisibleText(DataFileTradingPlat.userTitle);
		RegistrationPage.firstNameField(driver).sendKeys(DataFileTradingPlat.userFirstName);
		RegistrationPage.lastNameField(driver).sendKeys(DataFileTradingPlat.userLastName);
		WebElement questionField = RegistrationPage.questionDropDown(driver);
		Select questionFieldSelect = new Select(questionField);
		questionFieldSelect.selectByVisibleText(DataFileTradingPlat.userSecurityQuestion);
		RegistrationPage.answerField(driver).sendKeys(DataFileTradingPlat.userSecurityAnswer);
		RegistrationPage.confirmAnsField(driver).sendKeys(DataFileTradingPlat.userSecurityAnswer);
	}

	@When("^a user enters unique \"([^\"]*)\" , \"([^\"]*)\" and \"([^\"]*)\" in the relevant fields$")
	public void a_user_enters_unique_and_in_the_relevant_fields(String email, String username, String password) throws Throwable {
		RegistrationPage.emailField(driver).sendKeys(email);
		RegistrationPage.usernameField(driver).sendKeys(username);
		RegistrationPage.pwdField(driver).sendKeys(password);
		RegistrationPage.confirmPwdField(driver).sendKeys(password);
		paraUsername = username;
		paraPassword = password;
	}
	
	@When("^a user clicks on the submit button on the Registration page$")
	public void a_user_clicks_on_the_submit_button_on_the_Registration_page() throws Throwable {
		RegistrationPage.submitBtn(driver).click();
	}

	@Then("^confirm that an appropriate welcome message has appeared$")
	public void confirm_that_an_appropriate_welcome_message_has_appeared() throws Throwable {
		String actualWelcomeMessage = RegistrationConfirmedPage.welcomeMessage(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.welcomeMessage, actualWelcomeMessage);
		String actualUserNameMessage = RegistrationConfirmedPage.userNameMessage(driver).getText();
		Assert.assertEquals("Your username is " + paraUsername, actualUserNameMessage);
	}
	
	@When("^a user clicks on Login link$")
	public void a_user_clicks_on_Login_link() throws Throwable {
		RegistrationConfirmedPage.loginLink(driver).click();
	}

	@Then("^confirm that the user is correctly brought to the Login page$")
	public void confirm_that_the_user_is_correctly_brought_to_the_Login_page() throws Throwable {
		Assert.assertEquals(DataFileTradingPlat.homePageURL, driver.getCurrentUrl());
	}

	@When("^a user enters in the newly created account information on the Login page$")
	public void a_user_enters_in_the_newly_created_account_information_on_the_Login_page() throws Throwable {
		LoginPage.userNameField(driver).sendKeys(paraUsername);
		LoginPage.passwordField(driver).sendKeys(paraPassword);
		LoginPage.submitButton(driver).click();
	}

	@Then("^confirm that an appropriate user name message appears when logging in$")
	public void confirm_that_an_appropriate_user_name_message_appears_when_logging_in() throws Throwable {		
		String actualLoginMessage = HomePage.loginMessage(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.loginMessage, actualLoginMessage);
	}

}
