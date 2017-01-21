package com.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;

public class CommonFunctions extends StaticVariables {
	
	public void CommonFunctions()
	{
		ProjectDir = System.getProperty("user.dir");
		System.out.println("ProjectDir is : --->"+ProjectDir);
		File file = new File(ProjectDir+"\\Screenshots");
		
		boolean a = false;
		if(!file.exists())
		{
			a= file.mkdir();
		}
		if(a){
			System.out.println("Screenshot path is cratreds succesfully");
		}
		else
		{
			System.out.println("Screenbshot fifolder is already available");
		}
		ScreenshotsPath = ProjectDir+"\\Screenshots\\";
		
	}
	/******** to Enter data into an Edit box **************/

	public void EnterTextByID(String LocaterName, String Inputdata) {
		driver.findElement(By.id(LocaterName)).sendKeys(Inputdata);
	}

	/******* To click on Button **********************************/
	public void ClickByID(String Locatername) {
		driver.findElement(By.id(Locatername)).click();

	}
	/******* To click on Link **********************************/
	public void ClickByLinkText(String Locatername) {
		driver.findElement(By.linkText(Locatername)).click();

	}
	public void ClickByXpath(String Locatername) {
		driver.findElement(By.xpath(Locatername)).click();

	}

	//Wait
	public void wait(int time) throws Exception
	{
		time = time*1000;
		Thread.sleep(time);
	}
	
	//Open the Chrome Browser
	public void ChroemBrowser()
	{
		ProjectDir = System.getProperty("user.dir");
		String path = ProjectDir+"\\Drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver();
	}
	
	//Launch the URL
	public void EnterURL(String URL)
	{
		driver.navigate().to(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	/********Take Screenshots on Pass or Fail 
	 * @throws Exception **************/
	public void ScreenshotonPassFail(ITestResult testResults) throws Exception
	{
		ClassName = testResults.getTestClass().getName().trim();
		System.out.println("ClassName is :-->"+ClassName);
		MethodName =testResults.getName().trim();
		System.out.println("MethodName is :-->"+MethodName);
		String ClsNmMtdNm = ClassName+"_"+MethodName+"_";
		
		if(testResults.getStatus()!=ITestResult.FAILURE){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,new File(ScreenshotsPath+"Pass_"+ClsNmMtdNm+TimeStamp()+".png"));
		}
		if(testResults.getStatus()==ITestResult.FAILURE){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,new File(ScreenshotsPath+"Fail_"+ClsNmMtdNm+TimeStamp()+".png"));
		}
		
		
	}
	public static String TimeStamp()
	{
		Date d = new Date();
		System.out.println(d);//Sat Jan 07 08:00:35 GMT 2017
		DateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String time=df.format(d);
		System.out.println("timeL:-->"+time);
		return time;
	}
	
	//Click on any button using JAVA SCRIPT
	public void ClickUsingJavaScript(WebElement webelement)
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", webelement);
	}
	

	public static void takeScreenShot(String Name) throws IOException
	{
		
		String time = null;
		String path = "C:\\Users\\gpr-shivyog\\workspace\\Maven_Project\\Screenshots\\";
		File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f,new File(path+Name+TimeStamp()+".png"));
	}
	

}
