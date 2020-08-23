package pageobjects;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	public boolean waitUntilVisible(WebDriver driver, WebElement element){
		// wait until item is visible and return true
		// if it times out, return false
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (ElementNotVisibleException e){
			return false;
		}
	}
	
	public static boolean waitUntilNotVisible(WebDriver driver, WebElement element){
		// wait until item is NOT visible and return true
		// if it times out, return false
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.invisibilityOf(element));
			return true;
		} catch (Exception e){
			return false;
		}
	}
	
	public boolean waitUntilClickable(WebDriver driver, WebElement element){
		// wait until item is clickable and return true
		// if it times out, return false
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e){
			return false;
		}
	}

	public static void scrollIntoView(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static void mouseOver(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

}
