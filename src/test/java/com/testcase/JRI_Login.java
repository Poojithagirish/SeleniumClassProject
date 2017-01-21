package com.testcase;

import org.testng.annotations.Test;

import com.objects.JRI_Locaters;
import com.utilities.CommonFunctions;
import com.utilities.StaticVariables;

import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class JRI_Login extends StaticVariables
{
	CommonFunctions cfn = new CommonFunctions();
	JRI_Locaters obj = new JRI_Locaters();

		
	@AfterMethod
	public void takeScreenshotonPassorFail(ITestResult testResults) throws Exception
	{
		
		cfn.ScreenshotonPassFail(testResults);
		Thread.sleep(3000);
	}
	@Test
	public  void Login() throws IOException, Exception {
		String TDpath = "C:\\Users\\gpr-shivyog\\workspace\\Maven_Project\\TestData\\JRI.properties";
		FileInputStream f = new FileInputStream(TDpath);
		Properties prop = new Properties();
		prop.load(f);

		cfn.ChroemBrowser();
		cfn.EnterURL(prop.getProperty("URL"));
		cfn.ClickByLinkText(obj.LINK_SIGNIN);
		cfn.takeScreenShot("Before_Enter_the_Details_in_JRI_Login_page");
		cfn.EnterTextByID(obj.ID_USERNAME, prop.getProperty("Username"));
		
		cfn.EnterTextByID(obj.ID_PASSWORD, prop.getProperty("Password"));
		Thread.sleep(4000);
		cfn.takeScreenShot("After_Enter_the_Details_in_JRI_Login_page");
		cfn.ClickByID(obj.ID_SIGNIN_BUTTON);
		cfn.takeScreenShot("After_Clickon_LoginButton");
		Thread.sleep(2000);

	}

	@BeforeClass
	public void beforeClass() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
