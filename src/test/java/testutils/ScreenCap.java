package testutils;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

public class ScreenCap extends runner.TestRunner {

	public static String capture(String scenarioName) {
		DateTimeString dateTimeString = new DateTimeString();
        String formattedDateTime = dateTimeString.generate(true);
		
        // specify directory to save to
  		String dir = "target/screenshots/";
  		
  		// take screenshot
  		TakesScreenshot sc = (TakesScreenshot) driver;
  		File screencap = sc.getScreenshotAs(OutputType.FILE);
  		
  		// concatenate directory, datetime, scenario name
  		String dest = dir + formattedDateTime + "_" + scenarioName + ".png";
  		
  		// save screenshot
  		File destination = new File(dest);
  		try {
			FileHandler.copy(screencap, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
  		
  		return dest;
	}
}

