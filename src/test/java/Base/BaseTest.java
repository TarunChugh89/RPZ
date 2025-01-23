package Base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

import Factory.BrowserFactory;
import Factory.DriverFactory;
import Factory.ExtentFactory;
import Listener.ExtentReportListener;
import Utilities.PropertiesOperation;

@Listeners(ExtentReportListener.class)
public class BaseTest {
	
	//WebDriver driver;
	protected SoftAssert sft;
	protected ThreadLocal<ExtentTest> testStep= new ThreadLocal<ExtentTest>();
	protected ThreadLocal<SoftAssert> softassert= new ThreadLocal<SoftAssert>();
	
	public void setTestStep(String description)
	{
		testStep.set(report().createNode(description));
	}
	
	public ExtentTest testStep()
	{
		return testStep.get();
	}
	
	public SoftAssert getAssert()
	{
		return softassert.get();
	}
	
	
	@BeforeMethod
	public void init_Setup()
	{
		BrowserFactory bwf= new BrowserFactory();
		//sft=new SoftAssert();
		softassert.set(new SoftAssert());
		PropertiesOperation.init_property();
		DriverFactory.getInstance().setDriverFactory(bwf.init_driver(PropertiesOperation.prop.getProperty("browser")));
		
		
		
	}

	@AfterMethod
	public void init_Close()
	{
		//getAssert().assertAll();
		DriverContext().quit();
		
	}
	

	
	public WebDriver DriverContext()
	{
		return DriverFactory.getInstance().getDriverFactory();
	}
	
	public ExtentTest report()
	{
		
		return ExtentFactory.getInstance().getExtentFactory();
	}
	
}
