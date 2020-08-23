package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	@FindBy(id = "email") private WebElement emailField;
	
	@FindBy(id = "passwd") private WebElement passwordField;
	
	@FindBy(id = "SubmitLogin") private WebElement submitLoginBtn;
	
	@FindBy(css = ".info-account") private WebElement successMsg;
	
	@FindBy(id = "email_create") private WebElement emailCreateField;
	
	@FindBy(id = "SubmitCreate") private WebElement submitCreateBtn;
	

	
	public void enterCredentials(WebDriver driver, String emailAddress, String password) {
		waitUntilVisible(driver, emailField);
		emailField.sendKeys(emailAddress);
		passwordField.sendKeys(password);
	}
	
	public void enterNewUserEmail(String emailAddress) {
		emailCreateField.sendKeys(emailAddress);
	}
	
	public void submitLogin() {
		submitLoginBtn.click();
	}
	
	public void submitRegister() {
		submitCreateBtn.click();
	}
	
	public String getSuccessMessage() {
		return successMsg.getText();
	}

	
}
