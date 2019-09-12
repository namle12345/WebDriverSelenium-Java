package pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Navbar {

	public static WebElement transactionHistoryLink(WebDriver driver) {
		return driver.findElement(By.xpath("//*[contains(@id, 'tabs25')]/ul/li[6]/a"));
	}

}
