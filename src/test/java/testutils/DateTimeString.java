package testutils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeString {
	
	public String generate(boolean withTime) {
		String format;
		Calendar cal;
		
		// choose time format
		if(withTime==true) {
			// format with time
			format = "yyyyMMddHHmmss";
		} else {
			// format without time
			format = "yyyyMMdd";
		}
		
		cal = Calendar.getInstance();

        SimpleDateFormat inFormat = new SimpleDateFormat(format);
        String formattedDateTime = inFormat.format(cal.getTime());
        
        return formattedDateTime;
	}

}
