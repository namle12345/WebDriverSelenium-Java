package pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationConfirmedPage {
	public static WebElement welcomeMessage(WebDriver driver) {
		return driver.findElement(By.xpath("//div[contains(@id, 'main')]/h3"));
	}

	public static WebElement userNameMessage(WebDriver driver) {
		return driver.findElement(By.xpath("//div[contains(@id, 'main')]/p"));
	}

	public static WebElement loginLink(WebDriver driver) {
		return driver.findElement(By.linkText("login"));
	}
}
