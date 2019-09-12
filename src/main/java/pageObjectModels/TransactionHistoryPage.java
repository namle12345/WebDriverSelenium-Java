package pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TransactionHistoryPage {

	public static WebElement companyNameField(WebDriver driver) {
		return driver.findElement(By.xpath("//*[contains(@id, 'transaction_history')]/tbody/tr/td[1]"));
	}

	public static WebElement transactionTypeField(WebDriver driver) {
		return driver.findElement(By.xpath("//*[contains(@id, 'transaction_history')]/tbody/tr/td[2]"));
	}

	public static WebElement numberOfSharesField(WebDriver driver) {
		return driver.findElement(By.xpath("//*[contains(@id, 'transaction_history')]/tbody/tr/td[3]"));
	}

}
