package pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TransactionConfirmationPage {

	public static WebElement companyText(WebDriver driver) {
		return driver.findElement(By.xpath("//*[contains(@id, 'transaction_summary')]/table/tbody/tr[1]/td[2]"));
	}

	public static WebElement totalSharesText(WebDriver driver) {
		return driver.findElement(By.xpath("//*[contains(@id, 'transaction_summary')]/table/tbody/tr[3]/td[2]"));
	}

}
