package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import Environment.EnvironmentSelection;

public class PropertiesOperation {
	
	//reason this variable is static since Properties_init_property we are making static and it has to be used across all
	
	public static Properties prop= new Properties();
	
	
	public static Properties init_property()
	{
		
		try {
		EnvironmentSelection.EnvironmentSetting();
		FileInputStream file= new FileInputStream(EnvironmentSelection.environmentPath);
		prop.load(file);
		}
		catch(IOException e)
		{
			System.err.println("File not found due to "+ e.getMessage());
			System.exit(1);
		}
		
		return prop;
	}

}
