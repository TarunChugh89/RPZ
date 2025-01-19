package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONTokener;

import CustomException.JSONObjectFetchException;

public class JSONOperation {
	
	public JSONObject fetchJSONObject(String JSONPath,String key) 
	{
		JSONObject data= null;
		FileInputStream fs=null;
		try
		{
			fs= new FileInputStream(JSONPath);
			JSONTokener tokener= new JSONTokener(fs);
			JSONObject obj=new JSONObject(tokener);
			data=obj.getJSONObject(key);
		}
		
		catch(Exception e)
		{
			String errorMessage= String.format("Failed to fetch JSONObject due to: %s", e.getMessage());
			System.err.println(errorMessage);
			//throw new JSONObjectFetchException(errorMessage, e);
		}
		finally
		{
			if(fs!=null)
			{
				try {
				fs.close();
				}
				catch(IOException e)
				{
					System.err.println("Failed to close FileInputStream: " + e.getMessage());
				}
			}
		}
		return data;
	}
	
	public JSONObject nestedJSONObject(JSONObject obj,String key)
	{
		JSONObject nestedJSON= null;
		
		if(obj.has(key))
		{
			Object value= obj.get(key);
			if(value instanceof JSONObject)
			{
				nestedJSON= (JSONObject)value;
			}
			else
			{
				System.err.println("Value under the key ["+key+"] is not a JSON Object ");
			}
			
		}else{
			
			for(String nestedKey: obj.keySet())
			{
				Object value= obj.get(nestedKey);
				if(value instanceof JSONObject)
				{
					nestedJSON=nestedJSONObject((JSONObject)value, key);
				}
				if(nestedJSON!=null)
				{
					break;
				}
			}
			

		}
		
		return nestedJSON;
	}

}
