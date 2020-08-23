package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	
	@FindBy(css = "#columns > div.breadcrumb.clearfix > a") private WebElement homeBtn;

	@FindBy(linkText = "ORDER HISTORY AND DETAILS") private WebElement orderHistoryLink;
	
	@FindBy(className = "page-heading") private WebElement pageHeading;
	
	public String getHeading() {
		return pageHeading.getText();
	}
	
	public void goToOrderHistory(WebDriver driver) {
		waitUntilClickable(driver, orderHistoryLink);
		orderHistoryLink.click();
	}

}
