package stepdefination_UI;

import java.io.File;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelDataRead {
public static void main (String [] args) throws Exception{
	String Path = System.getProperty("user.dir");
	File src = new File(Path + "\\src\\test\\resources\\Properties\\DemoSheet.xlsx");
	XSSFWorkbook workbook = new XSSFWorkbook(src);
	XSSFSheet sheet = workbook.getSheet("Sheet1");
	String username1= sheet.getRow(1).getCell(0).getStringCellValue();
	System.out.println("First username value is "+username1);
	String username2= sheet.getRow(2).getCell(0).getStringCellValue();
	System.out.println("Second username value is "+username2);
	String username3= sheet.getRow(3).getCell(0).getStringCellValue();
	System.out.println("Third username value is "+username3);
	
	String password1= sheet.getRow(1).getCell(1).getStringCellValue();
	System.out.println("First password value is "+password1);
	String password2= sheet.getRow(2).getCell(1).getStringCellValue();
	System.out.println("Second password value is "+password2);
	String password3 = sheet.getRow(3).getCell(1).getStringCellValue();
	System.out.println("Third password value is "+password3);
	
	}
}