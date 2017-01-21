package com.testcase;

import org.testng.annotations.Test;

import com.objects.JRI_Locaters;
import com.utilities.CommonFunctions;
import com.utilities.StaticVariables;

import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterClass;

public class JRI_Registration extends StaticVariables {
	
	CommonFunctions cfn = new CommonFunctions();
	JRI_Locaters obj = new JRI_Locaters();

	
  @Test
  public void Registration() throws IOException, Exception {
		String TDpath = "C:\\Users\\gpr-shivyog\\workspace\\Maven_Project\\TestData\\JRI.properties";
		FileInputStream f = new FileInputStream(TDpath);
		Properties prop = new Properties();
		prop.load(f);

		cfn.ChroemBrowser();
		cfn.EnterURL(prop.getProperty("URL"));
		cfn.ClickByLinkText(obj.LINK_NEWACCOUNT);
		cfn.takeScreenShot("Before Sign up");
		cfn.EnterTextByID(obj.ID_NAME, prop.getProperty("Name"));
		cfn.EnterTextByID(obj.ID_MOBILE, prop.getProperty("Mobile"));
		cfn.EnterTextByID(obj.ID_EMAIL, prop.getProperty("Email"));
		cfn.EnterTextByID(obj.ID_PASSWRD, prop.getProperty("Passwrd"));
		cfn.ClickByID(obj.ID_CHECKBOX);
		cfn.takeScreenShot("After entering detils to sign up");
		cfn.ClickByID(obj.CREATE_ACCOUNT_BUTTON);
		
  }
  
  
  @BeforeClass
  public void beforeClass() {
  }

  
  
  @AfterClass
  public void afterClass()
  {
		driver.quit();
		}

}
