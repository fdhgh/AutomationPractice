package stepdefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.math.BigDecimal;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import pageobjects.*;
import testobjects.TestItem;

public class PurchaseItemsStepDefinitions extends runner.TestRunner {
	
	@Given("user is on Index Page")
	public void user_is_on_Index_Page() {
		if (!driver.getCurrentUrl().equals("http://automationpractice.com/")) {
    		driver.get("http://automationpractice.com/index.php");
      }
	}
	
	@When("the user adds item {int} in size {string} to the basket")
	public void the_user_adds_item_in_size_to_the_basket(Integer itemNo, String size) {
		IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
		
		// itemNo declared in feature file corresponds to item order on the index page
		indexPage.clickQuickView(driver, itemNo);

		QuickViewFrame quickViewFrame = PageFactory.initElements(driver, QuickViewFrame.class);
		// wait until quick view frame is visible
		quickViewFrame.switchTo(driver);
		
		quickViewFrame.changeSize(driver, size);
		
		// when a new item is added to the real basket, add the item to the basket test object
		// to keep track of items we think are in the basket to test against later
		testItem.add(new TestItem());
		
		// and set price by value displayed in quickview frame
		testItem.get(itemNo-1).setPrice(quickViewFrame.getPrice());
		
		quickViewFrame.addToCart(driver);
	}
	
	@When("user continues shopping")
	public void user_continues_shopping() {
		CartLayer cartLayer = PageFactory.initElements(driver, CartLayer.class);		
		cartLayer.clickContinue(driver);
	}
	
	@When("goes to checkout")
	public void goes_to_checkout() {
		CartLayer cartLayer = PageFactory.initElements(driver, CartLayer.class);
		cartLayer.clickProceed(driver);
	}
	
	@Then("basket contains {int} items")
	public void basket_contains_items(int expected) {
		CartPage cartPage = PageFactory.initElements(driver, CartPage.class);
	    int actual = cartPage.getQuantity();
	    Assert.assertEquals(expected, actual); 
	}
	
	@Then("item {int} has size {string}")
	public void item_has_size(Integer itemNo, String size) {
		CartPage cartPage = PageFactory.initElements(driver, CartPage.class);		
		String expectedItemSize = size;
	    String actualItemSize = cartPage.getItemSize(driver, itemNo);
	    
	    Assert.assertEquals(expectedItemSize, actualItemSize); 
	}
	
	@Then("item {int} has advertised price")
	public void item_has_size(Integer itemNo) {
		CartPage cartPage = PageFactory.initElements(driver, CartPage.class);		
		BigDecimal expectedItemPrice = testItem.get(itemNo-1).getPrice();
	    BigDecimal actualItemPrice = cartPage.getPrice(driver, itemNo);
	    
	    Assert.assertEquals(expectedItemPrice, actualItemPrice); 
	}
	
	@Then("total products is sum of items")
	public void total_products_is_sum_of_items() {
		CartPage cartPage = PageFactory.initElements(driver, CartPage.class);
		int numberOfItems = cartPage.numberOfItems();
		BigDecimal expected = new BigDecimal(0);
		
		// add up item prices on the page
		for(int i=0; i<numberOfItems; i++) {
			BigDecimal itemPrice = cartPage.getPrice(driver, i+1);
			expected = expected.add(itemPrice);
		}
		
		// compare to total listed
		BigDecimal actual = cartPage.getTotalProduct();
		
		Assert.assertEquals(expected, actual);
	}
	
	@Then("total equals total products plus shipping")
	public void total_equals_total_products_plus_shipping() {
		CartPage cartPage = PageFactory.initElements(driver, CartPage.class);
		
		// add up total products, tax, shipping to get expected result
	    BigDecimal expected = cartPage.getTotalProduct().add(cartPage.getTotalShipping()).add(cartPage.getTotalTax());
	    
	    // displayed total
	    BigDecimal actual = cartPage.getTotalPrice();
	    
	    Assert.assertEquals(expected, actual);    
	}
	

}

