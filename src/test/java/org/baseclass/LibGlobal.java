package org.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LibGlobal {
	
	public static  WebDriver driver;
	public static WebDriver launch() {
		WebDriverManager.chromedriver().setup();
	 driver=new ChromeDriver();
	return driver;	
	}
	public static void launchurl(String url) {
		driver.get(url);
	}
	public static void fill(WebElement element, String text) {
		element.sendKeys(text);
	}
	public static void clk(WebElement  element) {
		element.click();
	}
	public static String excelread(int ro,int ce) throws IOException {
		File file = new File("E:\\Eclipse_Aruna\\DemoSite\\Excel\\TestData.xlsx");	
		FileInputStream finstream = new FileInputStream(file);
		Workbook w=new XSSFWorkbook(finstream);
		Sheet s = w.getSheet("Sheet1");
		Row r = s.getRow(ro);
		Cell c = r.getCell(ce);
		int typ = c.getCellType();
		String name;
		if(typ==1) {
			name=c.getStringCellValue();
		}
		else if (typ ==0) {
			DateUtil.isCellDateFormatted(c);
			Date dt = c.getDateCellValue();
			SimpleDateFormat date=new SimpleDateFormat("dd-mm-yyyy");
			name=date.format(dt);
			
		}
		else {
			double d=c.getNumericCellValue();
			long l =(long) d;
			name=String.valueOf(l);
		}
return name;

		}
		public static void writreexcel(int ro, int clm, String data) throws IOException {
		File file = new File("E:\\Eclipse_Aruna\\DemoSite\\Excel\\TestData.xlsx");
		FileInputStream finstream = new FileInputStream(file);
		Workbook w=new XSSFWorkbook(finstream);
		Sheet s = w.getSheet("Sheet1");
		Row r = s.getRow(ro);
		Cell c = r.createCell(clm);
		c.setCellValue(data);
		FileOutputStream fout = new FileOutputStream(file);
		w.write(fout);

		}
		 public static void waitvisibleEle(WebElement ele) {
			 WebDriverWait w1=new WebDriverWait(driver,60);
			 w1.until(ExpectedConditions.elementToBeClickable(ele));

}
		 public static void scrolldown(WebElement ele) {
				JavascriptExecutor jk=(JavascriptExecutor)driver;
				jk.executeScript("arguments[0].scrollIntoView(false)", ele);
			}
		 public static void toSelect(WebElement ele,String text) {
			Select s=new Select(ele);
			s.selectByVisibleText(text);
		}
		 public static void SetValue(String i,WebElement ele) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('value','"+i+"')", ele);
		}
		 public static void waitUntillClickable( WebElement ele) {
			 WebDriverWait w=new WebDriverWait(driver,90);
			 w.until(ExpectedConditions.elementToBeClickable(ele));
					}
		 public static void waitTime() throws InterruptedException {
			Thread.sleep(3000);
		}

}
