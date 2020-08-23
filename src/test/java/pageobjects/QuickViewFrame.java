package pageobjects;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import testutils.CashConverter;

public class QuickViewFrame extends BasePage {
	
	@FindBy(id = "bigpic") private WebElement bigPic;
	
	@FindBy(id = "group_1") private WebElement sizeDropdown;
	
	@FindBy(id = "add_to_cart") private WebElement addToCartBtn;
	
	@FindBy(id = "our_price_display") private WebElement price;
	
	@FindBy(className = "fancybox-iframe") private WebElement thisFrame;
	
	private List<String> sizes = Arrays.asList("S", "M", "L");

	public void switchTo(WebDriver driver){
		waitUntilVisible(driver, thisFrame);
		driver.switchTo().frame(thisFrame);
	}
	
	public WebElement getBigPic(){
		return bigPic; 
	}
	
	public BigDecimal getPrice() {
		return CashConverter.strToDec(price.getText());
	}
	
	public void changeSize(WebDriver driver, String size) {
		if(sizes.contains(size)) {
		
			waitUntilClickable(driver, sizeDropdown);
			// select from dropdown by text
			Select sizeSelect = new Select(sizeDropdown);
			sizeSelect.selectByVisibleText(size);
		}
		
	}
	
	public void addToCart(WebDriver driver) {
		waitUntilClickable(driver, addToCartBtn);
		addToCartBtn.click();
		waitUntilNotVisible(driver, bigPic);
		driver.switchTo().defaultContent();
	}
	

}
