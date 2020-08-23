package stepdefinitions;
import java.util.concurrent.TimeUnit;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

import pageobjects.IndexPage;
import pageobjects.LoginPage;

public class LoginStepDefinitions extends runner.TestRunner {
	
    @Given("^user is on homepage$")
    public void user_is_on_homepage() {     
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
    }
    
    
    @When("^user navigates to Login page$")
    public void user_navigates_to_Login_Page() {
    	IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
    	// check if user already logged in
    	if(testUser.getLogin()==true) {
    		// log out first
    		indexPage.clickSignOutBtn(driver);
    		testUser.setLogin(false);
    	}
    	indexPage.clickSignInBtn(driver);
    }
    
    @When("^user enters username and password$")
    public void user_enters_username_and_Password() {
    	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
    	String userEmail = testUser.getEmail();
    	String userPassword = testUser.getPassword();
    	loginPage.enterCredentials(driver, userEmail, userPassword);
        loginPage.submitLogin();
        testUser.setLogin(true);
    }
    
    @Then("^success message is displayed$")
    public void success_message_is_displayed() {
    	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
    	String expected = "Welcome to your account. Here you can manage all of your personal information and orders.";
    	String actual = loginPage.getSuccessMessage();
        Assert.assertEquals(expected, actual); 
    }
    

}
