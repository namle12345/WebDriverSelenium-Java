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
import pageObjectModels.ResetPasswordConfirmationPage;
import pageObjectModels.ResetPasswordPage;
import testData.DataFileTradingPlat;
import cucumber.api.java.en.Then;

public class ResetPasswordStepDefinition {
	private WebDriver driver = null;
	private String tmpPassword = null;

	public ResetPasswordStepDefinition(BaseStepDefinition baseStepDefinition) {
		this.driver = baseStepDefinition.driver;
	}

	@Given("^a user is on the Login screen of the Trading Platform$")
	public void a_user_is_on_the_Login_screen_of_the_Trading_Platform() throws Throwable {
		driver.get(DataFileTradingPlat.homePageURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@When("^a user clicks on the Forgot Password link on the Check Balance page$")
	public void a_user_clicks_on_the_Forgot_Password_link_on_the_Check_Balance_page() throws Throwable {
		LoginPage.resetPasswordLink(driver).click();
	}

	@When("^a user enters in the customer's valid data on the Request Password Reset page$")
	public void a_user_enters_in_the_customer_s_valid_data_on_the_Request_Password_Reset_page() throws Throwable {
		ResetPasswordPage.usernameField(driver).sendKeys(DataFileTradingPlat.userUserName);
		WebElement questionField = ResetPasswordPage.questionDropDown(driver);
		Select questionFieldSelect = new Select(questionField);
		questionFieldSelect.selectByVisibleText(DataFileTradingPlat.userSecurityQuestion);
		ResetPasswordPage.answerField(driver).sendKeys(DataFileTradingPlat.userSecurityAnswer);
	}

	@When("^a user clicks on the Reset Password button on the Request Password Reset page$")
	public void a_user_clicks_on_the_Reset_Password_button_on_the_Request_Password_Reset_page() throws Throwable {
		ResetPasswordPage.resetPasswordButton(driver).click();
	}

	@Then("^confirm that the appropriate confirmation reset password message has appeared$")
	public void confirm_that_the_appropriate_confirmation_reset_password_message_has_appeared() throws Throwable {
		String actualResetMessage = ResetPasswordConfirmationPage.resetConfirmMessage(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.resetConfirmationMessage, actualResetMessage);
	}

	@Then("^confirm that the user can login with their new password on the Login screen$")
	public void confirm_that_the_user_can_login_with_their_new_password_on_the_Login_screen() throws Throwable {
		String newPasswordLine = ResetPasswordConfirmationPage.newPasswordField(driver).getText();
		newPasswordLine = newPasswordLine.replaceAll("\\s+", "");
		String[] splitPasswordLine = newPasswordLine.split(":", 2);
		tmpPassword = splitPasswordLine[1];
		ResetPasswordConfirmationPage.homeLink(driver).click();
		LoginPage.userNameField(driver).sendKeys(DataFileTradingPlat.userUserName);
		LoginPage.passwordField(driver).sendKeys(tmpPassword);
		LoginPage.submitButton(driver).click();
	}

	@Then("^confirm the appropriate login confirmation message has appeared$")
	public void confirm_the_appropriate_login_confirmation_message_has_appeared() throws Throwable {
		String actualLoginMessage = HomePage.loginMessage(driver).getText();
		Assert.assertEquals(DataFileTradingPlat.loginMessage, actualLoginMessage);
	}
}
