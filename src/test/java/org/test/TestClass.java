package org.test;

import java.io.IOException;

import org.baseclass.LibGlobal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.pojo.PojoClass;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestClass extends LibGlobal {
	
	static PojoClass pc;
	@BeforeSuite
	private void tc1() {
		launch();
		launchurl("https://www.liidaveqa.com/");
		driver.manage().window().maximize();
	}
	@Test
	private void Login() throws IOException, InterruptedException {
		pc=new PojoClass();
	clk(pc.getSignin());
		fill(pc.getUserName(),"lenproautomation8@lenqat.com");
		fill(pc.getPassWrd(), "Community17");
		clk(pc.getLoginSubmit());
		clk(pc.getIcon());
	if(	excelread(0,0).equalsIgnoreCase("salestool")) {
	clk(pc.getSalesTool());
	}
	if(excelread(1, 0).equalsIgnoreCase("BuildAProposal")){
	clk(pc.getBuildAProposal());	
	}
	waitTime();
	clk(pc.getSelectLead());
	waitUntillClickable(pc.getAddlead());
	clk(pc.getAddlead());
	}
	@Test(dependsOnMethods="Login")
	private void details() throws IOException, InterruptedException {
		pc=new PojoClass();
		writreexcel(0,4, "/src/test/resources/automation image.jpg");
		fill(pc.getFirstName(),excelread(2, 0));
		fill(pc.getLastName(), excelread(3, 0));
		String email = excelread(4, 0);
		fill(pc.getEmail(),email);
		String phonenu = excelread(5,0);
		fill(pc.getPhonenumber(),phonenu);
	scrolldown(pc.getUpdate());
	waitvisibleEle(pc.getCalander());
	String i=excelread(0,1);
	clk(pc.getCalander());
	WebElement date = driver.findElement(By.xpath("//a[text()="+i+"]"));
	clk(date);
	waitTime();
	SetValue(excelread(0,2), pc.getTime());
	scrolldown(pc.getSaveLead());
	clk(pc.getDocument());
	waitTime();
		clk(pc.getDocumentSelect());
		toSelect(pc.getDocumentSelect(),"OTHER" );
		clk(pc.getDocumentType());
		String path=System.getProperty("user.dir")+ excelread(0,3);
		pc.getDocFile().sendKeys(path);
	clk(pc.getAddtolead());
		String path1=System.getProperty("user.dir")+excelread(0, 4);
		waitTime();
	pc.getImgFile().sendKeys(path1);
	clk(pc.getSaveLead());
	waitTime();
	if(pc.getPopup().isEnabled()) {
		clk(pc.getPopup());
	}
	waitvisibleEle(pc.getSuccesful());
	Assert.assertTrue(pc.getSuccesful().getText().contains("Lead has been created/edited successfully."),"successful message not displayed");
	}}


}
