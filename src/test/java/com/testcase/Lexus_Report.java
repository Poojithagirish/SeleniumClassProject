package com.testcase;

import org.testng.annotations.Test;

import com.objects.JRI_Locaters;
import com.utilities.CommonFunctions;
import com.utilities.StaticVariables;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Lexus_Report extends StaticVariables {
	CommonFunctions cfn = new CommonFunctions();

	@Parameters("browser")
	@BeforeClass
	public void beforeclass(@Optional("chrome") String browser) {
		cfn.ChroemBrowser();

	}

	@Test
	public void f() throws Exception {
		
		String TestdataPath = ProjectDir+"\\TestData\\"+"Input.properties";
		System.out.println("TestdataPath-->"+TestdataPath);
		FileInputStream f = new FileInputStream(TestdataPath);
		Properties prop = new Properties();
		prop.load(f);		
		cfn.EnterURL(prop.getProperty("Lexus_URL"));
		
		int carCount = driver.findElements(By.xpath("//*[@class = 'codeweaversDerivativeSelect']")).size();
		
		System.out.println("carCount: -->"+carCount);
		int carIndex = 1;
		for(WebElement car : driver.findElements(By.xpath("//*[@id='codeweaversVehicles']//*[@class = 'codeweaversVehicleModel']")))
		{
			int Subcount = driver.findElements(By.xpath("//*[@class='codeweaversVehicleModel'][" + carIndex + "]//select[@class='codeweaversDerivativeSelect']/option")).size();
			for(WebElement we : driver.findElements(By.xpath("//*[@class='codeweaversVehicleModel'][" + carIndex + "]//select[@class='codeweaversDerivativeSelect']/option")))
			{
				if(!we.getText().contains("Please Select"))
				{
					we.click();
					Thread.sleep(1000);
					//Click on the next button
					WebElement Nexctbutton = driver.findElement(By.id("codeweaversNextStepButton"));
					Nexctbutton.click();
					
					
				}
				
			}
			
		}
			
	
		
	}

	@AfterMethod
	public void takeScreenshotonPassorFail(ITestResult testResults) throws Exception {
		cfn.ScreenshotonPassFail(testResults);
		Thread.sleep(3000);
	}

}
