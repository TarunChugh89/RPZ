package Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperation {
	
	    private String filePath;
	    private Sheet sh;

	    public ExcelOperation(String filePath,String sheetName) {
	        this.filePath = filePath;
	        File testDataFile = new File(filePath);
	        try {
	            Workbook wb = WorkbookFactory.create(testDataFile);
	            this.sh = wb.getSheet(sheetName);
	            if (this.sh == null) {
	                throw new RuntimeException("Sheet not found in Excel file.");
	            }
	        } catch (EncryptedDocumentException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public Object[][] getExcelData()
		{	
			int rowCount = sh.getLastRowNum(); // Get the number of rows
		    int colCount = sh.getRow(0).getLastCellNum(); 
			Object[][] excelData= new Object[rowCount][colCount];
			for(int i=1;i<=sh.getLastRowNum();i++)
			{
				for(int j=0; j<sh.getRow(0).getLastCellNum();j++ )
				{
					if (sh.getRow(i).getCell(j) != null) {
		                excelData[i-1][j] = sh.getRow(i).getCell(j).toString();
		            } else {
		                excelData[i-1][j] = ""; // Handle empty cells
		            }
					
				}
			}
			return excelData;
		}
		
	    
	    
	}

