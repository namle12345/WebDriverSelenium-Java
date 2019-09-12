package pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	public static WebElement balanceLink(WebDriver driver) {
		return driver.findElement(By.linkText("Check Balance"));
	}

	public static WebElement buyStockLink(WebDriver driver) {
		return driver.findElement(By.linkText("Buy Stock"));
	}

	public static WebElement loginMessage(WebDriver driver) {
		return driver.findElement(By.xpath("//div[contains(@id, 'main')]/h3"));
	}

	public static WebElement logoutLink(WebDriver driver) {
		return driver.findElement(By.linkText("logout"));
	}

}
