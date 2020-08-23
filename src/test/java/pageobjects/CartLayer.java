package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartLayer extends BasePage {
	
	@FindBy(id = "layer_cart") private WebElement layerCart;
	
	@FindBy(xpath = "//*[@id=\\\"layer_cart\\\"]/div[1]/div[1]/h2") private WebElement cartLayerText;
	
	@FindBy(xpath = "//*[@title='Continue shopping']") private WebElement continueShopping;
	
	@FindBy(xpath = "//*[@title='Proceed to checkout']") private WebElement proceedToCheckout;
	
	
	public void clickContinue(WebDriver driver) {
		// wait for the button to be clickable
		waitUntilClickable(driver, continueShopping);
		// click continue to continue shopping
		continueShopping.click();
		// switch back to the main frame
		driver.switchTo().defaultContent(); 
	}
	
	public void clickProceed(WebDriver driver) {
		// wait for the button to be clickable
		waitUntilClickable(driver, proceedToCheckout);
		// click proceed to checkout
		proceedToCheckout.click();
	}
}
