package Listener;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.internal.IResultListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Factory.ExtentFactory;
import Report.ExtentSparkReport;

public class ExtentReportListener implements IResultListener {
	
	public static ExtentReports report;
	public ExtentTest test;
	

	@Override
	public void onTestStart(ITestResult result) {
		test=report.createTest(result.getMethod().getMethodName());
		ExtentFactory.getInstance().SetExtentFactory(test);	
		System.out.println("The following test method has been started"+result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentFactory.getInstance().getExtentFactory().pass("The following method is passed"+result.getMethod().getMethodName());
		System.out.println("Test Passed: " + result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentFactory.getInstance().getExtentFactory().fail("The following method is failed"+result.getMethod().getMethodName());
		System.out.println("Test Failed: " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentFactory.getInstance().getExtentFactory().skip("The following method is skipped"+result.getMethod().getMethodName());
		System.out.println("Test Skipped: " + result.getMethod().getMethodName());
	
	}

	@Override
	public void onStart(ITestContext context) {
		
		report= ExtentSparkReport.createExtentTestReport(context);
		System.out.println("Test Report in Progress");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		report.flush();
		System.out.println("The following test suite is finished"+ context.getName());
		
	}
	
	

}
