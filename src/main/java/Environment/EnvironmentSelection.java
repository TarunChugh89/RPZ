package Environment;

public class EnvironmentSelection {
	
	public static String env="SIT";
	public static String environmentPath= "";
	
	//a static method can only access static variable/static method of same class
	

	public static void EnvironmentSetting()
	{
		
		switch(env.toLowerCase())
		{
		
		case "sit":
			environmentPath = "./src/test/resource/config/SIT.properties";
			break;
		default:
			throw new IllegalArgumentException("Invalid environment is selected, pausing the execution");
		
		}
		
	}
	
	

}
