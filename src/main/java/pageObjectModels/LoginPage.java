package pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	public static WebElement registerLink(WebDriver driver) {
		return driver.findElement(By.linkText("Register"));
	}

	public static WebElement userNameField(WebDriver driver) {
		return driver.findElement(By.name("j_username"));
	}

	public static WebElement passwordField(WebDriver driver) {
		return driver.findElement(By.name("j_password"));
	}

	public static WebElement submitButton(WebDriver driver) {
		return driver.findElement(By.name("submit"));
	}

	public static WebElement resetPasswordLink(WebDriver driver) {
		return driver.findElement(By.linkText("Forgot Password"));
	}

}
