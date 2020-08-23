package stepdefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import pageobjects.IndexPage;
import pageobjects.MyAccountPage;
import pageobjects.OrderHistoryPage;
import testutils.DateTimeString;

public class ReviewAddMessageStepDefinitions extends runner.TestRunner {
	
	@Given("user is on Order History page")
	public void user_is_on_order_history_page() {
		IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
		indexPage.clickMyAccount(driver);
		
		MyAccountPage accountPage = PageFactory.initElements(driver, MyAccountPage.class);
		accountPage.goToOrderHistory(driver);	
	}

	@When("user selects an item from order")
	public void user_selects_an_item_from_order() {
		OrderHistoryPage orderHistoryPage = PageFactory.initElements(driver, OrderHistoryPage.class);
	    String orderDate = testOrder.getOrderDate();
	    
	    orderHistoryPage.clickOrderLink(driver, orderDate);		
	}
	
	@When("user adds comment")
	public void user_adds_comment() {
		OrderHistoryPage orderHistoryPage = PageFactory.initElements(driver, OrderHistoryPage.class);
		DateTimeString commentTime = new DateTimeString();
		String comment = "This is the comment I'm leaving on " + commentTime.generate(true) + "!!!";
		
		testOrder.setComment(comment);
	    orderHistoryPage.addMessage(driver, comment);
	}
	
	@Then("comment appears under messages")
	public void comment_appears_under_messages() {
		OrderHistoryPage orderHistoryPage = PageFactory.initElements(driver, OrderHistoryPage.class);
	    String expected = testOrder.getComment();
	    String actual = orderHistoryPage.getLatestComment(driver);
	    
	    Assert.assertEquals(expected, actual);
	}
	
	@Then("item {int} has a colour of {string}")
	public void item_has_a_colour_of(int itemNo, String expected) {
		OrderHistoryPage orderHistoryPage = PageFactory.initElements(driver, OrderHistoryPage.class);
	    String actual = orderHistoryPage.getItemColour(driver, itemNo);
	    
	    Assert.assertEquals(expected, actual);
	}

}
