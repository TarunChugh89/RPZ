package Utilities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {
	
	public void writetoExternalExcel(String path, String sheetName,List<String> dropdownValues )
    {
    	Workbook workbook = new XSSFWorkbook();
    	Sheet sheet = workbook.createSheet(sheetName);
    	
    	
    	int rowIndex = 0;
    	for (String value : dropdownValues) {
    	    Row row = sheet.createRow(rowIndex++);
    	    row.createCell(0).setCellValue(value);
    	}
    	
    	try (FileOutputStream fileOut = new FileOutputStream(path)) {
    	    workbook.write(fileOut);
    	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    	
    }
	
	public void writeToExternalFile(String filePath, String sheetName, List<List<String>> data)
	{
		Workbook workbook= new XSSFWorkbook();
		Sheet sheet= workbook.createSheet(sheetName);
		
		for(int i=0;i<data.size();i++)
		{
			Row row=sheet.createRow(i);
			List<String> rowData= data.get(i);
			
			for(int j=0;j<rowData.size();j++)
			{
				Cell cell=row.createCell(j);
				cell.setCellValue(rowData.get(j));
			}
		}
		
		FileOutputStream fileout;
		try {
			fileout = new FileOutputStream(filePath);
			workbook.write(fileout);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
