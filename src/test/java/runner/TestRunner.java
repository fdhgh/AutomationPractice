package runner;
import org.junit.BeforeClass;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import testobjects.TestItem;
import testobjects.TestOrder;
import testobjects.TestUser;


@RunWith(Cucumber.class)
@CucumberOptions(
		features={"src/test/java/features"},
		glue= {"stepdefinitions"},
		plugin = { "pretty", "html:target/htmlreports" }
		)

public class TestRunner {
	
	public static WebDriver driver = null;
	
	public static TestUser testUser = new TestUser();
	public static TestOrder testOrder = new TestOrder();
	public static List<TestItem> testItem = new ArrayList<TestItem>();
    
    @BeforeClass
    public static void setup() throws Throwable {    
    	// choose browser: -Dbrowser=chrome OR -Dbrowser=firefox
    	String browser = System.getProperty("browser");
    	driver = createDriver(browser);
    	driver.manage().window().maximize();
    }
    
    @AfterClass
    public static void teardown() {    
    	driver.quit(); 
    }
    
	public static WebDriver createDriver(String browserName) throws Exception {
	    	
		switch(browserName) {
        case "firefox":
        	String GeckoDriverPath = "src\\test\\resources\\webdrivers\\geckodriver.exe"; //System.getenv("GECKO_DRIVER_PATH");
        	System.setProperty("webdriver.gecko.driver",GeckoDriverPath);
        	driver = new FirefoxDriver();
            break;
        case "chrome":
        	String ChromeDriverPath = "src\\test\\resources\\webdrivers\\chromedriver.exe"; //System.getenv("CHROME_DRIVER_PATH");
        	System.setProperty("webdriver.chrome.driver",ChromeDriverPath);
        	driver = new ChromeDriver();
            break;
        default:
            throw new Exception("Unsupported browser: " + browserName);
        }
		return driver;
    }


    
}
