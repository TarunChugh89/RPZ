package Base;

public class TestBase extends BaseTest {
	
	
	public void AssertEqual(String expected, String actual)
	{
		if(actual.equals(expected))
		{
			testStep().pass(String.format("Match.Expected: %s, Actual: %s",expected,actual));
		}
		else
		{
			testStep().fail(String.format("MisMatch.Expected: %s, Actual: %s",expected,actual));
			getAssert().fail(String.format("MisMatch.Expected: %s, Actual: %s",expected,actual));
			
		}
	}
	
	public void AssertContains(String expected, String actual)
	{
		if(actual.trim().contains(expected.trim()))
		{
			testStep().pass(String.format("Actual Contain Expected .Expected: %s, Actual: %s",expected,actual));

		}
		else
		{
			testStep().fail(String.format("Actual Did not Contain Expected .Expected: %s, Actual: %s",expected,actual));
			getAssert().fail(String.format("Actual Did not Contain Expected .Expected: %s, Actual: %s",expected,actual));
			
		}
	}
	
	public void AssertTrue(Boolean condition,String locator, String message)
	{
		if(condition)
		{
			testStep().pass(String.format("Assertion Passed.Locator %s is %s",locator,message));
		}
		else
		{
			testStep().fail(String.format("Assertion Failed.Locator %s is not %s",locator,message));
			getAssert().fail(String.format("Assertion Failed.Locator %s is not %s",locator,message));
		}
	}
	

}
