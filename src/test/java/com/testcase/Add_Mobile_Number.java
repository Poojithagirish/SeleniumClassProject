package com.testcase;

import org.testng.annotations.Test;

import com.objects.JRI_Locaters;
import com.utilities.CommonFunctions;
import com.utilities.StaticVariables;

import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class Add_Mobile_Number extends StaticVariables {

	JRI_Login jri = new JRI_Login();
	CommonFunctions cfn = new CommonFunctions();
	JRI_Locaters obj = new JRI_Locaters();

	@AfterMethod
	public void takeScreenshotonPassorFail(ITestResult testResults) throws Exception {

		cfn.ScreenshotonPassFail(testResults);
		cfn.wait(2);
	}

	@Test(description = "Add Mobile Number")
	public void Add_Mobnum() throws IOException, Exception {

		jri.Login();
		cfn.wait(2);
		cfn.ClickByXpath(obj.XPATH_MYACCOUNT);
		cfn.ClickByXpath(obj.XPATH_DIRECTORY);
		cfn.ClickByXpath(obj.XPATH_ADDMOBNUM);

		new Select(driver.findElement(By.id("ddlpopMobileSP"))).selectByVisibleText("Aircel");
		Thread.sleep(2000);
		driver.findElement(By.id("txtpopMobileNo")).sendKeys("8560543451");
		driver.findElement(By.id("txtpopMobileNickname")).sendKeys("Selenium");
		new Select(driver.findElement(By.xpath("//*[@id='ddlpopMobileLocation']"))).selectByVisibleText("Orissa");
		// driver.findElement(By.id("ddlpopMobileLocation")).sendKeys("Haryana");
		driver.findElement(By.id("btnPopupAddMobile")).click();
		

	}

	@Test(description = "Edit Mobile Number")
	public void Edit_Mobnum() throws IOException, Exception {

		jri.Login();
		cfn.wait(2);
		cfn.ClickByXpath(obj.XPATH_MYACCOUNT);
		cfn.ClickByXpath(obj.XPATH_DIRECTORY);

		driver.findElement(By.id("listingtable_btnGridEdit_0")).click();

	}

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

}
