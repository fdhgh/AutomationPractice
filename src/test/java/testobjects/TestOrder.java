package testobjects;

import testutils.DateTimeString;

public class TestOrder {
	
	private String orderDate;
	private String comment;
	
	public String getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate() {
		DateTimeString dateTimeString = new DateTimeString();
		orderDate = dateTimeString.generate(false);
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String newComment) {
		comment = newComment;
	}
		
}
	