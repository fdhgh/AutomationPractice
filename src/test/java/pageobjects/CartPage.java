package pageobjects;

import java.math.BigDecimal;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testutils.CashConverter;

public class CartPage extends BasePage {
	
	
	@FindBy(id = "cart_title") private WebElement cartTitle;
	
	@FindBy(id = "first_item") private WebElement firstItem;
	
	@FindBy(className = "cart_item") private List<WebElement> cartItems;
	
	@FindBy(partialLinkText = "Color : ") private List<WebElement> itemSpecs;
	
	@FindBy(id = "summary_products_quantity") private WebElement productsQuantity;
	
	@FindBy(id = "total_price") private WebElement totalPrice;
	
	@FindBy(id = "total_product") private WebElement totalProduct;
	
	@FindBy(id = "total_shipping") private WebElement totalShipping;
	
	@FindBy(id = "total_tax") private WebElement totalTax;
	
	@FindBy(linkText = "Proceed to checkout") private WebElement proceedToCheckout;
	
	@FindBy(name = "processAddress") private WebElement processAddress;
	
	@FindBy(name = "processCarrier") private WebElement processCarrier;
	
	@FindBy(id = "address_invoice") private WebElement billingAddress;
	
	@FindBy(id = "cgv") private WebElement termsOfServiceCheck;
	
	@FindBy(className = "bankwire") private WebElement payByWire;
	
	@FindBy(id = "cart_navigation") private WebElement cartNavigation;
	
	@FindBy(className = "cheque-indent") private WebElement chequeIndent;
	

	public int numberOfItems() {
		return cartItems.size();
	}
	
	public String getItemSize(WebDriver driver, int itemNo) {
		WebElement itemSpec = itemSpecs.get(itemNo-1);
		scrollIntoView(driver, itemSpec);
		String specsText = itemSpec.getText();
		String itemSize = specsText.substring(specsText.length()-1);
		return itemSize;
	}
	
	public BigDecimal getPrice(WebDriver driver, int itemNo) {
		// construct CSS selector by item number
		String locator = "span[id^='total_product_price_" + itemNo + "']";
		WebElement price = driver.findElement(By.cssSelector(locator));
		scrollIntoView(driver, price);
		return CashConverter.strToDec(price.getText());
	}
	
	public BigDecimal getTotalProduct() {
		return CashConverter.strToDec(totalProduct.getText());
	}
	
	public BigDecimal getTotalShipping() {
		return CashConverter.strToDec(totalShipping.getText());
	}
	
	public BigDecimal getTotalTax() {
		return CashConverter.strToDec(totalTax.getText());
	}
	
	public BigDecimal getTotalPrice() {
		return CashConverter.strToDec(totalPrice.getText());
	}
	
	public int getQuantity() {
		String summaryText = productsQuantity.getText();
		String quantityString = summaryText.substring(0,1);
		int quantityInt = Integer.parseInt(quantityString);
		
		return quantityInt;
	}
	
	public void proceedToAddress(WebDriver driver) {
		proceedToCheckout.click();
		waitUntilClickable(driver, processAddress);
	}
	
	public void proceedToShipping(WebDriver driver) {
		processAddress.click();
		waitUntilClickable(driver, termsOfServiceCheck);
	}
	
	public void proceedToPayment(WebDriver driver) {
		if(termsOfServiceCheck.isSelected()==false) {
			termsOfServiceCheck.click();
		}
		processCarrier.click();
		waitUntilClickable(driver, payByWire);
	}
	
	public void proceedToPayByWire(WebDriver driver) {
		payByWire.click();
		waitUntilVisible(driver, chequeIndent);
	}
	
	public void proceedToConfirm(WebDriver driver) {
		cartNavigation.submit();
	}
	
	public String getCartTitle() {
		return cartTitle.getText();
	}

}
