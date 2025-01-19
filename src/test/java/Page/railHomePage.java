package Page;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import Base.BasePage;

public class railHomePage extends BasePage {
	
	By sourceStationInput= By.xpath("//input[@id=\"txtStationFrom\"]");
	By sourceStationList= By.xpath("//div[contains(@id,\"AutocompleteContainter\")][1]//div//div[normalize-space(@title) != \"\"]");
	By calendarDepartureButton= By.xpath("//input[@title=\"Select Departure date for availability\"]");

	public railHomePage(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}
	
	public void inputSourceStation(String sourceStation)
	{
		inputText(sourceStationInput, sourceStation, "SourceStation");	
	}
	
	public void selectInputStation(String index)
	{
		SelectFromListbyCollection(sourceStationList, index, "SourceStationList", "index");
	}
	
	public List<String> fetchSourceStationProbableList()
	{
		return retrieveStationName(sourceStationList,"title");
	}
	
	public List<String> retrieveStationName(By locator,String attribute)
	{
		WebDriverWait wt= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		List<WebElement> webElmt=wt.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		
		List<String> stationName= new ArrayList<>();
		for(WebElement e: webElmt)
		{
			stationName.add(e.getAttribute(attribute));
		}
		
		return stationName;
	}
	
	public String retreiveSelectedStation()
	{
		return getAttribute(sourceStationInput, "value", "Station Selected");
	}
	
	public void selectDepartureDate(String futureDate)
	{
		String[] datePart= futureDate.split("-");
		String day= datePart[0];
		String monthDate= datePart[1]+"-"+ datePart[2];
		
		String futureDatelocator= String.format("//td[text()='%s']//ancestor::table[@class='Month']//td[text()='%s']", monthDate, day);
		System.out.println(futureDatelocator);
		click(By.xpath(futureDatelocator), futureDate);
	}
	
	public void clickonDepatureDate()
	{
		click(calendarDepartureButton, "DepartureCalendar");
	}

}
