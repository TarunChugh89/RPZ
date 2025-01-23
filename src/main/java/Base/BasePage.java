package Base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import Utilities.PropertiesOperation;

public class BasePage {
	
	protected WebDriver driver;
	protected ExtentTest test;
	public static final int timeOut=10;
	
	public BasePage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	public void click(By locator, String locatorValue)
	{
		int attempt=1;
		while(attempt<=3)
		{
		try
		{
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			wt.until(ExpectedConditions.elementToBeClickable(locator)).click();
			test.info(String.format("Click Operation is performed at [%s]",locatorValue));
			return;
		}
		catch(StaleElementReferenceException e)
		{
			System.out.println(String.format("Retrying for [%d]st time due to StaleElementException",attempt));
			attempt++;
		}
		catch(Exception e)
		{
			handleException("Click", locatorValue, e,null,true);
			return;
		}
	}
	
		test.fail(String.format("Failed to perform click operation at [%s] after 3 attempts due to StaleElement Exception", locatorValue));
		
	}
	
	public void inputText(By locator, String value,String locatorValue)
	{
		
		try
		{
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			WebElement webElmt=wt.until(ExpectedConditions.visibilityOfElementLocated(locator));
			webElmt.clear();
			webElmt.sendKeys(value);
			test.info(String.format("Text is input at [%s] with value as [%s]",locatorValue,value));
		}
		catch(Exception e)
		{
			handleException("InputText", locatorValue, e,null,true);
		}
	}
	
	
	public String getText(By locator,String locatorValue)
	{
		
		try
		{
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wt.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
		}
		catch(Exception e)
		{
			return handleException("GetText", locatorValue, e,"", false);
		}
	}
	
	public String getAttribute(By locator, String value,String locatorValue)
	{
		
		try
		{
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wt.until(ExpectedConditions.visibilityOfElementLocated(locator)).getAttribute(value);
		}
		catch(Exception e)
		{
			return handleException("GetAttribute", locatorValue, e,"", false);
		}
	}
	
	public void inputKeys(By locator, Keys Action,String locatorValue)
	{
		
		try
		{
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			WebElement webElmt=wt.until(ExpectedConditions.visibilityOfElementLocated(locator));
			webElmt.clear();
			webElmt.sendKeys(Action);
			test.info(String.format("Key is input at [%s] with value as [%s]",locatorValue,Action));
		}
		catch(Exception e)
		{
			handleException("InputText", locatorValue, e,null,true);
		}
	}
	
	public WebElement getElement(By locator,String locatorValue)
	{
		try {
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wt.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		catch(Exception e)
		{
			return handleException("GetElement", locatorValue, e, null, false);
		}
	}
	
	public void performByAction(By locator,String ActionType,String locatorValue)
	{
		try
		{
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			WebElement ele=wt.until(ExpectedConditions.visibilityOfElementLocated(locator));
			Actions act= new Actions(driver);
			
			switch(ActionType.toLowerCase())
			{
			case "Click":
				act.click(ele).perform();
				break;
			case "DoubleClick":
				act.doubleClick(ele).perform();
				break;
			case "RightClick":
				act.contextClick(ele).perform();
				break;
			case "Hover":
				act.moveToElement(ele).perform();
				break;			
			default:
				test.fail("Unsupported Action type");
				throw new IllegalArgumentException("Unsupported Action type"+ ActionType);
			}
		
			test.info(String.format("[%s] operation is performed at [%s] locator value", ActionType,locatorValue));
		}
		catch(Exception e)
		{
			handleException(ActionType, locatorValue, e,null,true);
		}
		
	}

	public void SelectFromList(By locator, String value,String selectionType, String locatorValue)
	{
		try
		{
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			WebElement webElmt=wt.until(ExpectedConditions.visibilityOfElementLocated(locator));
			Select sel= new Select(webElmt);
			
			switch(selectionType.toLowerCase())
			{
			case "index":
				int index=Integer.parseInt(value);
				sel.selectByIndex(index);
				break;
			case "value":
				sel.selectByValue(value);
				break;
			case "text":
				sel.selectByVisibleText(value);
				break;
			default:
				test.fail("Unsupported Action type");
				throw new IllegalArgumentException("Unsupported selection type: " + selectionType);
			}
			
			
			test.info(String.format("Element with %s = [%s] selected at [%s]", selectionType, value, locatorValue));
		}
		catch(Exception e)
		{
			handleException(selectionType, locatorValue, e,null,true);
		}
	}
	
	public void SelectFromListbyCollection(By locator, String value, String locatorValue, String selectionType)
	{
		
		try {
			Boolean flag=false;
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			List<WebElement> webElmt=wt.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			
			switch(selectionType.toLowerCase())
			{
			case "value":
				for(WebElement e: webElmt)
				{
					if(e.getAttribute(value).equals(value))
					{
						e.click();
						flag=true;
						break;
					}
				}
				break;
			case "text":
				for(WebElement e: webElmt)
				{
					if(e.getText().equals(value))
					{
						e.click();
						flag=true;
						break;
					}
				}
				break;
			case "index":
				int index = Integer.parseInt(value)-1;
				 if(index>0 && index< webElmt.size())
				 {
					 webElmt.get(index).click();
					 flag=true;
					 break;
				 }
			default:
				test.fail("Unsupported selection type");
				throw new IllegalArgumentException("Unsupported selection type: " + selectionType);
			}
		
			
		
		if(!flag)
		{
			test.warning(String.format("No element found in the list with given criteria at [%s]", locatorValue));
		}
		else
		{
			test.info(String.format("Element with %s = [%s] selected at [%s]", selectionType, value, locatorValue));
		}
			
		}
		catch(Exception e)
		{
			handleException(selectionType, locatorValue, e,null,true);
		}
	}
	
	
	public void AlertActions(By locator, String Action, String locatorValue)
	{
		try
		{
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			switch(Action.toLowerCase())
			{
			case "accept":
				wt.until(ExpectedConditions.alertIsPresent()).accept();
				break;
			case "decline":
				wt.until(ExpectedConditions.alertIsPresent()).dismiss();
				break;
			case "capturemessage":
				wt.until(ExpectedConditions.alertIsPresent()).getText();
			default:
				test.fail("Unsupported Alert Selection type");
				throw new IllegalArgumentException("Unsupported Selection for Alert is: " + Action);
			}
			
			test.info(String.format("Alert [%s] operation is performed at [%s] locator value", Action,locatorValue));
		}
		catch(Exception e)
		{
			handleException(Action, locatorValue, e,null,true);
		}
	}
	
	public void PerformByJS(By locator,String value,String Action,String locatorValue)
	{
		try {
			JavascriptExecutor js= (JavascriptExecutor) driver;
			switch(Action.toLowerCase())
			{
			case "view":
				js.executeScript("argument.scrollIntoView(true)", getElement(locator,locatorValue));
				break;
			case "scrollup":
				break;
			case "scrolldown":
				break;
			case "click":
				js.executeScript("argument.click[0]", getElement(locator,locatorValue));
				break;
			case "upload":
				break;
			default:
				test.fail("Unsupported JS Selection type");
				throw new IllegalArgumentException("Unsupported Selection for JavaScript: " + Action);
			}
			
			test.info(String.format("JavaScript [%s] operation is performed at [%s] locator value", Action,locatorValue));
			
		}
		catch(Exception e)
		{
			handleException(Action, locatorValue, e,null,true);
		}
	}
	
	
	//<T>= make method generic while T type indicate return type
	public <T> T handleException(String operation,String locatorValue,Exception e, T defaultValue, boolean failTest)
	{		
		String errorMessage= String.format(" [%s] Operation is failed at [%s].Error is [%s]",operation,locatorValue,e.getMessage());
		
		System.err.print(errorMessage);
		if(failTest)
		{
			test.fail(errorMessage);
			Assert.fail(errorMessage);
		}
		else
		{
			test.warning(errorMessage);
		}
		
		return defaultValue;
		
		
	}
	
	public boolean isDisplayed(By locator,String locatorValue)
	{
		try {
			WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			return wt.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
			
		}
		catch(Exception e)
		{
			return handleException("isDisplayed", locatorValue, e, false,false);
		}
	}
	
	
}
