package Factory;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

	
	ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>();
	
	private DriverFactory()
	{
		
	}
	
	public static DriverFactory instance= new DriverFactory();
	
	public static DriverFactory getInstance()
	{
		return instance;
	}
	
	public void setDriverFactory(WebDriver param)
	{
		driver.set(param);
	}
	
	public WebDriver getDriverFactory()
	{
		return driver.get();
	}
	
	
}
