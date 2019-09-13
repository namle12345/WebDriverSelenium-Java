package pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BuyingSharesPage {

	public static WebElement amountToBuyField(WebDriver driver) {
		return driver.findElement(By.name("amount"));
	}
	
	public static WebElement costPerShareField(WebDriver driver) {
		return driver.findElement(By.xpath("//div[contains(@id, 'number_shares')]/table/tbody/tr[2]/td"));
	}

	public static WebElement calculateButton(WebDriver driver) {
		return driver.findElement(By.id("calcBtn"));
	}

	public static WebElement confirmButton(WebDriver driver) {
		return driver.findElement(
				By.xpath("//div[contains(@id, 'transaction_summary')]/form/input[contains(@type, 'submit')]"));
	}
	
	public static WebElement transactionPriceField(WebDriver driver) {
		return driver.findElement(By.xpath("//div[contains(@id, 'transaction_summary')]/form/table/tbody/tr[1]/td[2]"));
	}
	
	public static WebElement amountToBuySummary(WebDriver driver) {
		return driver.findElement(By.xpath("//div[contains(@id, 'transaction_summary')]/form/table/tbody/tr[2]/td[2]"));
	}
	
	public static WebElement balanceAfterTransaction(WebDriver driver) {
		return driver.findElement(By.xpath("//div[contains(@id, 'transaction_summary')]/form/table/tbody/tr[3]/td[2]"));
	}
	
	public static WebElement currentAccountBalance(WebDriver driver) {
		return driver.findElement(By.xpath("//div[contains(@id, 'choose_account')]/table/tbody/tr[2]/td[2]"));
	}
}
