package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {
	

	public static void captureScreenshot(WebDriver driver,String testID) throws IOException
	{
		
		String dateAndTime = LocalDateTime.now().toString();
		dateAndTime = dateAndTime.replace(':', '-');
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		File destination = new File("F:\\Software Testing\\Automation\\Screenshots\\Test_"+testID+" "+dateAndTime+".jpeg");
		
		FileHandler.copy(source, destination);
		
	}
	
	public static String getData(String sheetName,int rowIndex, int columnIndex) throws EncryptedDocumentException, IOException
	{
		
		String data;
		String path = "F:\\Software Testing\\TestData\\FlipkartTestData.xlsx";
		
		FileInputStream file = new FileInputStream(path);
		
		Sheet sheet = WorkbookFactory.create(file).getSheet(sheetName);
		
		try
		{
		   data = sheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue();
		   
		}
		
		catch(IllegalStateException ref)
		{
			double value = sheet.getRow(rowIndex).getCell(columnIndex).getNumericCellValue();
			if(value%1==0.0) 
			{
		      long l = (long)value;
		      data = String.valueOf(l);
			}
			else
			{
				long l = (long)(value/1);
				float k = (float)(value%1);
				String decimal = Float.toString(k);
				decimal = decimal.substring(1);
				data = Long.toString(l)+decimal;
		    }
		
		}
		
		return data;
	}

}
