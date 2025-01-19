package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.sql.Statement;
import java.util.HashMap;



public class DatabaseOperation {
	
	public HashMap<String,String> getDBData(String connection, String username,String password, String query)
	{
		Connection con= null;
		Statement smt= null;
		ResultSet rs=null;
		
		HashMap<String,String> hashData= new HashMap<String,String>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(connection,username,password);
			smt=con.createStatement();
			rs=smt.executeQuery(query);
			ResultSetMetaData md= rs.getMetaData();
			
			if(rs.next())
				{
				for(int i=1;i<=md.getColumnCount();i++)
					{
				hashData.put(md.getColumnName(i).toString(), rs.getString(i)!=null?rs.getString(i):"" );
					}
				}
			else
			{
				System.out.println("No result retreived for the query executed");
			}
				
		return hashData;		
		}
		catch(Exception e)
		{
			String errorMessage = "SQL operation failed due to the following reason: " + e.getMessage();
		    
		    System.err.println(errorMessage);
		    throw new RuntimeException("Test Automation is stopped due to SQL failure", e);
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(con!=null)
				con.close();
				if(smt!=null)
				smt.close();
			}
			catch(Exception e)
			{
				System.err.println("Unable to close the connection"+e.getMessage());
			}
			
		}
		
		
		
		
		
	}

}
