package pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BuyingSharesPage {

	public static WebElement amountToBuyField(WebDriver driver) {
		return driver.findElement(By.name("amount"));
	}

	public static WebElement calculateButton(WebDriver driver) {
		return driver.findElement(By.id("calcBtn"));
	}

	public static WebElement confirmButton(WebDriver driver) {
		return driver.findElement(
				By.xpath("//div[contains(@id, 'transaction_summary')]/form/input[contains(@type, 'submit')]"));
	}
}
