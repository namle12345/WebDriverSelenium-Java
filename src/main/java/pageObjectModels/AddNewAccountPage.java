package pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddNewAccountPage {

	public static WebElement currencyDropDown(WebDriver driver) {
		return driver.findElement(By.name("currency"));
	}

	public static WebElement submitButton(WebDriver driver) {
		return driver.findElement(By.xpath("//tr/td/input[contains(@type, 'submit')]"));
	}

}
