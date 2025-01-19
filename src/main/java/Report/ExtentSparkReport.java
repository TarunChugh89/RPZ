package Report;

import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Environment.EnvironmentSelection;

public class ExtentSparkReport {
	
	public static String outputFolder="./build/";
	public static String filePath="TestReport.html";
	
	public static ExtentReports createExtentTestReport(ITestContext context)
	{
		try {
		ExtentSparkReporter reporter= new ExtentSparkReporter(outputFolder+filePath);
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setReportName("Automation Report");
		ExtentReports report= new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Environment", EnvironmentSelection.env);
		report.setSystemInfo("Test Suite type", context.getName());
		return report;
		}
		catch(Exception e)
		{
			
			System.err.println("Failed to create Extent Report"+ e.getMessage());
			throw new RuntimeException("Failed to initialize Report");
		}
		
		
	}

}
