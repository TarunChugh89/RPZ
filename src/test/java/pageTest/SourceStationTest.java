package pageTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.TestBase;
import Page.railHomePage;
import Utilities.DateOperation;
import Utilities.ExcelOperation;
import Utilities.ExcelWriter;
import Utilities.JSONOperation;

public class SourceStationTest extends TestBase {
	
	
	@DataProvider(name="provideFirstTestData")
	public Object[][] testDataCreation()
	{
		return new Object[][] {
			{"DEL","4",30}
		};
	}
	
	@Test(dataProvider = "provideFirstTestData")
	public void firstTest(String location, String index, long days)
	{

		setTestStep("First Test");
		DriverContext().navigate().to("https://erail.in");
		railHomePage rhp= new railHomePage(DriverContext(), testStep());
		rhp.inputSourceStation(location);
		List<String> sourceStationList= rhp.fetchSourceStationProbableList();
		testStep().info("Probabale Stations are"+ sourceStationList.toString());
		rhp.selectInputStation(index);
		System.out.println("Station selected is "+ rhp.retreiveSelectedStation());
		testStep().info("Station selected is "+ rhp.retreiveSelectedStation());
		ExcelWriter ew= new ExcelWriter();
		//ew.writetoExternalExcel("C:\\Eclipse\\Workspace\\Rapifuzz\\src\\test\\resource\\TestData\\RunTimeSourceStation.xlsx", "sourceStation", sourceStationList);
		DateOperation dop= new DateOperation();
		String currentDate=dop.getCurrentDate("d-MMM-yy");
		String futureDepartureDate= dop.addDatetoStandardFormat(currentDate, "d-MMM-yy", "days", days);
		rhp.clickonDepatureDate();
		rhp.selectDepartureDate(futureDepartureDate);
		ExcelOperation eop= new ExcelOperation("C:\\Eclipse\\Workspace\\Rapifuzz\\src\\test\\resource\\TestData\\SourceStation.xlsx", "Station");
		List<String> excelList= new ArrayList<>();
		for(Object[] row : eop.getExcelData())
		{
			for(Object cell : row)
			{
				excelList.add(cell.toString());
			}
		}
		
		ew.writeToExternalFile("C:\\Eclipse\\Workspace\\Rapifuzz\\src\\test\\resource\\TestData\\RunTimeSourceStation.xlsx", "Stationcompare", compareValue(excelList,sourceStationList));
//		JSONOperation jsop= new JSONOperation();
//		JSONObject jsondata= jsop.fetchJSONObject("C:\\Eclipse\\Workspace\\Rapifuzz\\src\\test\\resource\\TestData\\InputStation.json", "stations");
//		
//		for(int i=1;i<=jsondata.length();i++)
//		{	String temp= String.format("station%d", i);
//			AssertEqual(jsondata.getString(temp), sourceStationList.get(i-1));
//		}
		
		for(int i=0; i<excelList.size();i++)
		{
			AssertEqual(excelList.get(i), sourceStationList.get(i));
		}
		
		if(excelList.equals(sourceStationList))
		{
			System.out.println("Dropdown matches expected station names.");
			testStep().pass("Dropdown matches expected station names.");
		}
		else
		{
			System.out.println("DropDown Station did not match with Expected Station.Mismatch");
			testStep().fail("DropDown Station did not match with Expected Station.Mismatch.");
			sft.fail("DropDown Station did not match with Expected Station.Mismatch");
		}
		
	}

	public List<List<String>> compareValue(List<String> Expected, List<String> Actual)
	{
		List<List<String>> results= new ArrayList<>();
		
		for(int i=0;i<Expected.size();i++)
		{
			List<String> row= new ArrayList<String>();
			String expected= Expected.get(i);
			String actual= Actual.get(i);
			String value= expected.equals(actual)?"true":"false";
			row.add(expected);
			row.add(actual);
			row.add(value);
			results.add(row);
		}
		
		return results;
	}
	
}
