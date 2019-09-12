package pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResetPasswordConfirmationPage {

	public static WebElement resetConfirmMessage(WebDriver driver) {
		return driver.findElement(By.xpath("//div[contains(@id, 'main')]/h3"));
	}

	public static WebElement newPasswordField(WebDriver driver) {
		return driver.findElement(By.xpath("//div[contains(@id, 'main')]/p"));
	}

	public static WebElement homeLink(WebDriver driver) {
		return driver.findElement(By.linkText("Home"));
	}
}
