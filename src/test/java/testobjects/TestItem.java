package testobjects;

import java.math.BigDecimal;

public class TestItem {
	
	private String size = "S"; // default size is small
	
	private String colour;
	
	private BigDecimal price;
	
	public String getSize() {
		return size;
	}
	
	public void setSize(String newSize) {
		size = newSize;
	}
	
	public String getColour() {
		return colour;
	}
	
	public void setColour(String newColour) {
		colour=newColour;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal newPrice) {
		price = newPrice;
	}
	
	public void setPriceFromString(String newPriceString) {
		
		if(newPriceString.substring(0,1) == "$") {
			newPriceString.substring(1);
		}
		
		BigDecimal newPrice = new BigDecimal(newPriceString);
		setPrice(newPrice);
	}


}
