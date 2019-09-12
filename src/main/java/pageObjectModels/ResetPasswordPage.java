package pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResetPasswordPage {

	public static WebElement usernameField(WebDriver driver) {
		return driver.findElement(By.name("username"));
	}

	public static WebElement questionDropDown(WebDriver driver) {
		return driver.findElement(By.name("question"));
	}

	public static WebElement answerField(WebDriver driver) {
		return driver.findElement(By.name("answer"));
	}

	public static WebElement resetPasswordButton(WebDriver driver) {
		return driver.findElement(By.name("submit"));
	}

}
