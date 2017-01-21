package com.testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

import com.objects.Locaters;
import com.utilities.CommonFunctions;
import com.utilities.StaticVariables;



public class Gmail_LoginTest extends StaticVariables{
	// Create an Reference/Object another class to call variables from parent
	// class
	static Locaters obj = new Locaters();
	//to call common functions first create reference for CommonFunction Class
	static CommonFunctions cfn = new CommonFunctions();

	public static void main(String[] args) throws IOException, Exception {
		
		//to call input data from external location use fileinputstream
		String PathOfthePropetyFile = "C:\\Users\\gpr-shivyog\\workspace\\Maven_Project\\TestData\\Input.properties";
		FileInputStream testdata = new FileInputStream(PathOfthePropetyFile);
		//we will use properties call to call the inputdata from property file
		Properties prop = new Properties();
		//to load the entire input values
		prop.load(testdata);
		
		
		
		//to call chrome browser
		String path = "C:\\Users\\gpr-shivyog\\workspace\\Maven_Project\\Drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver();
		
		//to call URL from property file
		driver.get(prop.getProperty("Gmail_URL"));
		Thread.sleep(3000);
		cfn.takeScreenShot("gmailHomepage");
		//driver.findElement(By.id(obj.ID_Email_Editbox)).sendKeys(prop.getProperty("Email"));
		cfn.EnterTextByID(obj.ID_Email_Editbox, prop.getProperty("Email"));
		//		driver.findElement(By.id(obj.ID_Next_Button)).click();
		cfn.ClickByID(obj.ID_Next_Button);
		Thread.sleep(3000);
		cfn.EnterTextByID(obj.ID_Password_Editbox, prop.getProperty("Password"));
		cfn.ClickByID(obj.ID_SignIn_Button);	
		cfn.takeScreenShot("Signbutton");
		
		
	}
	
	

}
