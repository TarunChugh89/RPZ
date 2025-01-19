package Factory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	
	private WebDriver driver;
	
	public WebDriver init_driver(String browser)
	{
		
			if(browser.isEmpty()|| browser==null)
			{
				throw new IllegalArgumentException("No browser is Passed, please pass the the browser ");
			}
			switch(browser.toLowerCase())
			{
			
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver= new ChromeDriver();
				break;
			case "mozilla":
				WebDriverManager.firefoxdriver().setup();
				driver= new FirefoxDriver();
				break;
			default:
				throw new IllegalArgumentException("Invalid browser is passed, please pass the correct browser");
			
			}
			
		return driver;
	}

}
