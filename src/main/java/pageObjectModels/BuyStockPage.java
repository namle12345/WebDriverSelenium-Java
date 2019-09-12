package pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import testData.DataFileTradingPlat;

public class BuyStockPage {

	public static WebElement gCompanies(WebDriver driver) {
		return driver.findElement(By.linkText("G"));
	}

	public static WebElement radioButton(WebDriver driver) {
		WebElement tableRow = driver.findElement(By.id(DataFileTradingPlat.companySymbol));
		return tableRow.findElement(By.name("symbol"));
	}

	public static WebElement buyButton(WebDriver driver) {
		return driver.findElement(By.id("submitBtn"));
	}
}
