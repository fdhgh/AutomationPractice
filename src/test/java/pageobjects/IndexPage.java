package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndexPage extends BasePage {
	
	@FindBy(linkText = "Sign in") private WebElement signInBtn;
	
	@FindBy(linkText = "Sign out") private WebElement signOutBtn;
	
	@FindBy(id = "header_logo") private WebElement headerLogo;
	
	@FindBy(className = "login") private WebElement loginLink;
	
	@FindBy(className = "logout") private WebElement logoutLink;
	
	@FindBy(id = "homefeatured") private WebElement homeFeatured;
	
	@FindBy(className = "quick-view") private List<WebElement> quickViewBtns;
	
	@FindBy(className = "account") private WebElement myAccountLink;
	
	@FindBy(className = "product_img_link") private List<WebElement> productImgLinks;
	
	
	public void clickSignInBtn(WebDriver driver) {
		waitUntilClickable(driver, signInBtn);
		signInBtn.click();
	}
	
	public void clickSignOutBtn(WebDriver driver) {
		waitUntilClickable(driver, signOutBtn);
		signOutBtn.click();
		waitUntilClickable(driver, signInBtn);
	}
	
	public void clickQuickView(WebDriver driver, int itemNo) {
		waitUntilVisible(driver,homeFeatured);
		
		WebElement quickViewBtn = quickViewBtns.get(itemNo-1);
		WebElement productImgLink = productImgLinks.get(itemNo-1);
		
		// mouse over the desired item
		scrollIntoView(driver,productImgLink);
		mouseOver(driver,productImgLink);
		waitUntilClickable(driver,quickViewBtn);
		quickViewBtn.click();
	}

	public void clickMyAccount(WebDriver driver) {
		waitUntilClickable(driver, myAccountLink);
		myAccountLink.click();
	}


}
