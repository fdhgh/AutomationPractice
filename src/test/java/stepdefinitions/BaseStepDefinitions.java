package stepdefinitions;

import org.openqa.selenium.support.PageFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pageobjects.CartPage;
import pageobjects.IndexPage;
import pageobjects.LoginPage;
import testutils.ScreenCap;

public class BaseStepDefinitions extends runner.TestRunner {
	
	
	@Before
	public void loginProcess(){
    	if(testUser.getLogin()==false) {
    		driver.get("http://automationpractice.com/index.php");
    	
	    	IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
	    	
	    	indexPage.clickSignInBtn(driver);
	    	
	    	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	    	String userEmail = testUser.getEmail();
	    	String userPassword = testUser.getPassword();
	    	
	    	loginPage.enterCredentials(driver, userEmail, userPassword);
	        loginPage.submitLogin();
	        
	        testUser.setLogin(true);
    	}
    }
	
	@After
	public void completeAndLogout(Scenario scenario) {
		// could be split into 3 After methods
		// but order of execution is not guaranteed
		
		// 1. take a screenshot if the test failed
		screenshotOnFailure(scenario);
		
		// 2. complete purchase at the end of View Basket scenario
		completeOrder(scenario);
		
		// 3. logout
		logout();
	}
	
	
	public void screenshotOnFailure(Scenario scenario) {
    	if (scenario.isFailed()) {
    		String scenarioName = scenario.getName().replaceAll("\\s+","");
    		ScreenCap.capture(scenarioName);
    	}
	}
	
	public void completeOrder(Scenario scenario) {
		String scenarioName = scenario.getName();
		if(scenarioName.equals("View basket")) {
			CartPage cartPage = PageFactory.initElements(driver, CartPage.class);
	    	// complete the purchase process paying by wire
		    cartPage.proceedToAddress(driver);
		    cartPage.proceedToShipping(driver);
		    cartPage.proceedToPayment(driver);
		    cartPage.proceedToPayByWire(driver);
		    cartPage.proceedToConfirm(driver);
		    testOrder.setOrderDate();
		    // clear the list of items once the basket is empty
			testItem.clear();
		}
	}
	
	public void logout() {
		IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
		indexPage.clickSignOutBtn(driver);
		testUser.setLogin(false);
	}
	
}      
    

