package com.testcase;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.objects.JRI_Locaters;
import com.utilities.CommonFunctions;
import com.utilities.StaticVariables;

import jxl.Sheet;
import jxl.Workbook;

public class MyProfile extends StaticVariables {
	JRI_Login jri = new JRI_Login();
	CommonFunctions cfn = new CommonFunctions();
	JRI_Locaters obj = new JRI_Locaters();

	@AfterMethod
	public void takeScreenshotonPassorFail(ITestResult testResults) throws Exception {

		cfn.ScreenshotonPassFail(testResults);
		cfn.wait(2);
	}

	@Test(description = "My Profile>>EditProfile")
	public void EditProfile() throws IOException, Exception {
		String excelpath = "C:\\Users\\gpr-shivyog\\workspace\\Maven_Project\\TestData\\TD.xls";
		FileInputStream f = new FileInputStream(excelpath);
		Workbook w = Workbook.getWorkbook(f);
		Sheet s = w.getSheet("MyProfile");

		jri.Login();
		cfn.wait(2);
		cfn.ClickByXpath(obj.XPATH_MYACCOUNT);
		cfn.ClickByXpath(obj.XPATH_MYPROFILE);
		cfn.ClickByXpath(obj.XPATH_EDITINFO);
		cfn.wait(2);

		// to get the text from application before modify the details
		
		
		WebElement Name = driver.findElement(By.xpath(".//*[@id='cust_name']"));
		String BeforeName = Name.getText();
		cfn.wait(2);
		driver.findElement(By.xpath("//*[@id='cust_name']")).clear();

		// Get data from Excel file

		driver.findElement(By.xpath("//*[@id='cust_name']")).sendKeys(s.getCell(2, 1).getContents());
		String AfterName = driver.findElement(By.xpath("//*[@id='lblName']")).getText();

		// Validate Before and After NAME element text
		//System.out.println("BeforeName:-->" + BeforeName);
		//System.out.println("AfterName:-->" + AfterName);
		//Assert.assertNotEquals(BeforeName, AfterName);
		//System.out.println("Program successfully executed...");
		
		cfn.wait(1);
		driver.findElement(By.xpath("//*[@id='cust_address']")).clear();
		driver.findElement(By.xpath("//*[@id='cust_address']")).sendKeys(s.getCell(2, 2).getContents());
		
		cfn.wait(1);
		driver.findElement(By.xpath("//*[@id='cust_city']")).clear();
		driver.findElement(By.xpath("//*[@id='cust_city']")).sendKeys(s.getCell(2, 3).getContents());
		
		cfn.wait(1);
		driver.findElement(By.xpath("//*[@id='ddlState']")).click();
		//driver.findElement(By.xpath("//*[@id='ddlState']")).clear();
		driver.findElement(By.xpath("//*[@id='ddlState']")).sendKeys(s.getCell(2, 4).getContents());
		
		cfn.wait(1);
		driver.findElement(By.xpath("//*[@id='cust_pincode']")).clear();
		driver.findElement(By.xpath("//*[@id='cust_pincode']")).sendKeys(s.getCell(2, 5).getContents());
		
		//cfn.wait(1);
		//driver.findElement(By.xpath("//*[@id='cust_dob']")).click();
		//driver.findElement(By.xpath("//*[@id='cust_dob']")).sendKeys(s.getCell(2, 7).getContents());
		
		cfn.wait(1);
		driver.findElement(By.xpath("//*[@id='cust_mobileno']")).clear();
		driver.findElement(By.xpath("//*[@id='cust_mobileno']")).sendKeys(s.getCell(2, 8).getContents());
		
		cfn.ClickByXpath(".//*[@id='imgbtnSubmit']");
		cfn.ClickByXpath(".//*[@id='content']/table/tbody/tr[2]/td/span/a/img");


	}
}
