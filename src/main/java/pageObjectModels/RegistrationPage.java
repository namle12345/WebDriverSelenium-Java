package pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage {

	public static WebElement titleDropDown(WebDriver driver) {
		return driver.findElement(By.name("title"));
	}

	public static WebElement firstNameField(WebDriver driver) {
		return driver.findElement(By.id("firstName"));
	}

	public static WebElement lastNameField(WebDriver driver) {
		return driver.findElement(By.id("lastName"));
	}

	public static WebElement emailField(WebDriver driver) {
		return driver.findElement(By.id("email"));
	}

	public static WebElement usernameField(WebDriver driver) {
		return driver.findElement(By.id("username"));
	}

	public static WebElement pwdField(WebDriver driver) {
		return driver.findElement(By.id("password"));
	}

	public static WebElement confirmPwdField(WebDriver driver) {
		return driver.findElement(By.id("confirmPassword"));
	}

	public static WebElement questionDropDown(WebDriver driver) {
		return driver.findElement(By.name("question"));
	}

	public static WebElement answerField(WebDriver driver) {
		return driver.findElement(By.name("answer"));
	}

	public static WebElement confirmAnsField(WebDriver driver) {
		return driver.findElement(By.name("confirmAnswer"));
	}

	public static WebElement submitBtn(WebDriver driver) {
		return driver.findElement(By.xpath("//tr/td/input[contains(@type, 'submit')]"));
	}

	public static WebElement resetBtn(WebDriver driver) {
		return driver.findElement(By.xpath("//tr/td/input[contains(@type, 'reset')]"));
	}

}
