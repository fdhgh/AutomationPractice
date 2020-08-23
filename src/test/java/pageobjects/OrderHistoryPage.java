package pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class OrderHistoryPage extends BasePage {
	
	
	@FindBy(id = "order-list") private WebElement orderList;
	
	@FindBy(className = "color-myaccount") private List<WebElement> historyLinks;
	
	@FindBy(id = "block-order-detail") private WebElement orderDetail;
	
	@FindBy(name = "id_product") private WebElement productDropdown;
	
	@FindBy(name = "msgText") private WebElement messageTextField;
	
	@FindBy(id = "sendOrderMessage") private WebElement messageForm;
	
	@FindBy(className = "history_date") private List<WebElement> historyDates;
	
	@FindBy(xpath = "//*[@id=\"block-order-detail\"]/div[5]/table/tbody/tr/td[2]") private WebElement latestComment;
	
	@FindBy(className = "alert-success") private WebElement alertSuccess;
	
	public void clickOrderLink(WebDriver driver, String myDateHour) {
		waitUntilVisible(driver, orderList);
		
		WebElement orderLink = null;
		
		// loop through dates to find a match for the expected date and hour
		// the first most recent match will be selected
		for(int i=0; i<historyLinks.size();i++) {
			String orderFullDateTime = historyDates.get(i).getAttribute("data-value");
			String orderDate = orderFullDateTime.substring(0, 9);
			
			// if the date is found, get the corresponding order element
			if(orderDate.equals(myDateHour)) {
				orderLink =  historyLinks.get(i);
				break;
			}
		}
		
		// if none found get the most recent one
		if(orderLink==null) {
			orderLink =  historyLinks.get(0);
		}

		orderLink.click();
		waitUntilVisible(driver, orderDetail);

	}
	
	public void selectItemToComment(int itemNo) {
		// select from dropdown by item index
		// index starts at 0 but first item is --Choose--
		Select productSelect = new Select(productDropdown);
		productSelect.selectByIndex(itemNo); 
	}
	
	
	public void addMessage(WebDriver driver, String messageText) {
		messageTextField.sendKeys(messageText);
		messageForm.submit();
		waitUntilVisible(driver, alertSuccess);
	}
	
	public String getLatestComment(WebDriver driver) {
		waitUntilVisible(driver, latestComment);
		scrollIntoView(driver, latestComment);
		return latestComment.getText();
	}
	
	public String getItemColour(WebDriver driver, int itemNo) {
		// construct CSS selector by item number
		String locator = "//*[@id=\"order-detail-content\"]/table/tbody/tr[" + itemNo + "]/td[2]/label";
		WebElement itemDetails = driver.findElement(By.xpath(locator));
		String itemDetailString = itemDetails.getText();
		
		scrollIntoView(driver, itemDetails);
		
		// calculate where the colour name begins and ends		
		String colourMarker = "Color : ";
		String sizeMarker = ", Size : ";
		
		int startIndex = itemDetailString.indexOf(colourMarker) + colourMarker.length();
		int endIndex = itemDetailString.indexOf(sizeMarker);
		
		// extract colour
		String colour = itemDetailString.substring(startIndex,endIndex);
		return colour;
		
	}	

	

}
