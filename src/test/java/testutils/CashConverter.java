package testutils;

import java.math.BigDecimal;

public class CashConverter {
	
	public static BigDecimal strToDec(String price) {
		// strip currency
		String priceStr = price.substring(1);
		// convert to BigDecimal type
		BigDecimal priceDec = new BigDecimal(priceStr);
		return priceDec;
	}

}
