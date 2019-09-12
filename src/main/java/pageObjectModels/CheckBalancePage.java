package pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckBalancePage {

	public static WebElement addNewAccountLink(WebDriver driver) {
		return driver.findElement(By.linkText("Add New Account"));
	}

	public static WebElement addFundsButton(WebDriver driver) {
		return driver.findElement(By.xpath("//tbody/tr[3]/td[4]/form/input[4]"));
	}

	public static WebElement defaultAccountButton(WebDriver driver) {
		return driver.findElement(By.xpath("//tbody/tr[3]/td[5]/form/input[3]"));
	}

	public static WebElement creditField(WebDriver driver) {
		return driver.findElement(By.name("credit"));
	}

	public static WebElement submitButton(WebDriver driver) {
		return driver.findElement(By.id("submitBtn"));
	}

	public static WebElement homeLink(WebDriver driver) {
		return driver.findElement(By.linkText("Home"));
	}

	public static WebElement USDCurrencyCell(WebDriver driver) {
		return driver.findElement(By.xpath("//tbody/tr[3]/td[2]"));
	}

	public static WebElement USDBalanceCell(WebDriver driver) {
		return driver.findElement(By.xpath("//tbody/tr[3]/td[3]"));
	}
}
