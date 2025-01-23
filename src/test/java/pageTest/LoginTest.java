package pageTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.TestBase;
import Page.loginPage;
import Utilities.ExcelOperation;

public class LoginTest extends TestBase {
	
	
	@DataProvider(name="testData")
	public Object[][] provideTestData()
	{
		ExcelOperation eop= new ExcelOperation(System.getProperty("user.dir")+"\\src\\test\\resource\\TestData\\Login.xlsx", "Credentials");
		return eop.getExcelData();
	}
	
	@Test(dataProvider = "testData")
	public void login(String userName, String password, String isValid)
	{
		try {
		setTestStep("The first test");
		DriverContext().navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		loginPage lp= new loginPage(DriverContext(), testStep());
		lp.submitcreds(userName, password);
		if(isValid.equals("TRUE"))
		{
			AssertTrue(lp.isDashBoardDisplayed(), "DashBoard", "Displayed");
		}
		else
		{
			AssertTrue(lp.isErrorMessageDisplayed(), "ErrorMessage", "Displayed");
		}

		}
		finally
		{
			getAssert().assertAll();
		}
	}

}
