package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

import Base.BasePage;

public class loginPage extends BasePage {

	public loginPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
		
	}
	
	By userName=By.xpath("//input[@name=\"username\"]");
	By password= By.xpath("//input[@name=\"password\"]");
	By submitBtn=By.xpath("//button[@type=\"submit\"]");
	By dashboardTitle= By.xpath("//h6[text()=\"Dashboards\"]");
	By errorMessage= By.xpath("//p[text()=\"Invalid credentials\"]");
	
	public void submitcreds(String name,String pwd)
	{
		inputText(userName, name, "UserName");
		inputText(this.password, pwd, "Password");
		click(submitBtn, "LoginBtn");
	}

	public boolean isDashBoardDisplayed()
	{
		return isDisplayed(dashboardTitle, "DashBoard Title");
			 
	}
	
	public boolean isErrorMessageDisplayed()
	{	
		return isDisplayed(errorMessage, "ErrorMesssage");
	}
	
	
}


