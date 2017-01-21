package com.testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.html.Option;
import com.google.common.base.Function;
import com.objects.LexusObjects;
import com.utilities.CommonFunctions;

public class Lexus extends com.utilities.StaticVariables {
	public String TestDataPath = "";
	PrintWriter writer;

	CommonFunctions cfn = new CommonFunctions();
	LexusObjects obj = new LexusObjects();

	@BeforeClass
	@Parameters("browser")
	public void beforeclass(@Optional("chrome") String browser) throws IOException, InterruptedException {
		cfn.ChroemBrowser();
		System.out.println("ScreenshotsPath: " + ScreenshotsPath);
	}

	@AfterClass
	public void End() throws IOException {
		driver.quit();
	}

	@AfterMethod
	public void StatusScreenShots(ITestResult testResult) throws Exception {
		cfn.ScreenshotonPassFail(testResult);
		Thread.sleep(1000);
	}

	@Test
	public void LexusCar() throws Exception {
		String TestdataPath = ProjectDir + "\\TestData\\" + "Input.properties";
		File file = new File(TestdataPath);
		Properties prop = new Properties();

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		driver.get("https://www.lexus.co.uk/finance/quick-finance-quote/#QuickFinanceQuote");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Thread.sleep(3000);
		writer = new PrintWriter(ProjectDir + "\\TestData\\" +"\\LexusResults\\"+"LexusQFQResults" + cfn.TimeStamp() + ".txt");
		FileWriter csv = new FileWriter(ProjectDir + "\\TestData\\"+"\\LexusResults\\" + "LexusQFQResults" + cfn.TimeStamp() + ".csv");

		String header = "Car Name" +"," + "Model Name" + "," + "Cash Price" + "," + "Term" + "," + "First Monthly Payment" + "," + "Next Monthly Payments" + "," + "Customer Deposit" + "," + "Centre Deposit Contribution" + "," + "Finance Deposit Allowance" + "," + "Amount of Credit" + ","
				+ "Guaranteed Future Value/Optional Final Payment" + "," + "Total Amount Payable" + "," + "Fixed Rate of Interest" + "," + "Representative APR" + "," + "Date";
		//to move header details to notepad
		writer.println(header);
		//to move header details to csv
		csv.write(header + "\n");

		int carCount = driver.findElements(By.xpath(".//div[@id='codeweaversVehicles']//div[@class='codeweaversVehicleModel']")).size();
		//implisit wait
		//Explisit wait
		//fulient wait
		while (carCount < 3) {
			carCount = driver.findElements(By.xpath(".//div[@id='codeweaversVehicles']//div[@class='codeweaversVehicleModel']")).size();
		}
		int carindex = 1;
		for (WebElement car : driver.findElements(By.xpath(".//div[@id='codeweaversVehicles']//div[@class='codeweaversVehicleModel']"))) {

			int subCount = driver.findElements(By.xpath(".//*[@class='codeweaversVehicleModel'][" + carindex + "]//select[@class='codeweaversDerivativeSelect']/option")).size();
			while (subCount < 1) {
				Thread.sleep(500);
				subCount = driver.findElements(By.xpath(".//*[@class='codeweaversVehicleModel'][" + carindex + "]//select[@class='codeweaversDerivativeSelect']/option")).size();
			}

			for (WebElement we : driver.findElements(By.xpath(".//*[@class='codeweaversVehicleModel'][" + carindex + "]//select[@class='codeweaversDerivativeSelect']/option"))) {
				if (!we.getText().contains("Please Select")) {
					we.click();
					Thread.sleep(500);

					// Click on Next button
					WebElement Nextbtn = driver.findElement(By.id("codeweaversNextStepButton"));
					cfn.ClickUsingJavaScript(Nextbtn);
					// cfn.ClickByID("codeweaversNextStepButton");
					Thread.sleep(2000);

					int CarNameLength = driver.findElement(By.xpath(obj.car_Name)).getText().length();
					while (CarNameLength < 1) {
						Thread.sleep(500);
						CarNameLength = driver.findElement(By.xpath(obj.car_Name)).getText().length();
						System.out.println("CarNameLength : " + CarNameLength);
					}

					String car_Name, modelName, CashPrice, Term, First_Monthly_Payment, Next_22_Monthly_Payments, Customer_Deposit, CentreDepositContribution, FinanceDepositAllowance, Amount_of_Credit, Guaranteed_Future_ValueOptional_Final_Payment, Total_Amount_Payable, Fixed_Rate_of_Interest,
							Representative_APR;
					car_Name = modelName = CashPrice = Term = First_Monthly_Payment = Next_22_Monthly_Payments = Customer_Deposit = CentreDepositContribution = FinanceDepositAllowance = Amount_of_Credit = Guaranteed_Future_ValueOptional_Final_Payment = Total_Amount_Payable = Fixed_Rate_of_Interest = Representative_APR = "NA";

					// CashPrice
					car_Name = driver.findElement(By.xpath(obj.car_Name)).getText();

					modelName = driver.findElement(By.xpath(obj.modelName)).getText();

					CashPrice = driver.findElement(By.xpath(obj.CashPrice)).getText();
					
					Term = driver.findElement(By.xpath(obj.Term)).getText(); 
					
					First_Monthly_Payment = driver.findElement(By.xpath(obj.First_Monthly_Payment)).getText();
					
					Next_22_Monthly_Payments = driver.findElement(By.xpath(obj.Next_22_Monthly_Payments)).getText();
					
					Customer_Deposit = driver.findElement(By.xpath(obj.Customer_Deposit)).getText();

					if (driver.findElements(By.xpath(obj.CentreDepositContribution)).size() > 0) {
						CentreDepositContribution = driver.findElement(By.xpath(obj.CentreDepositContribution)).getText();
					}

					if (driver.findElements(By.xpath(obj.FinanceDepositAllowance)).size() > 0) {
						FinanceDepositAllowance = driver.findElement(By.xpath(obj.FinanceDepositAllowance)).getText();
					}

					Amount_of_Credit = driver.findElement(By.xpath(obj.Amount_of_Credit)).getText();
					Guaranteed_Future_ValueOptional_Final_Payment = driver.findElement(By.xpath(obj.Guaranteed_Future_ValueOptional_Final_Payment)).getText();
					Total_Amount_Payable = driver.findElement(By.xpath(obj.Total_Amount_Payable)).getText();
					Fixed_Rate_of_Interest = driver.findElement(By.xpath(obj.Fixed_Rate_of_Interest)).getText();
					Representative_APR = driver.findElement(By.xpath(obj.Representative_APR)).getText();

					String finalOutput = car_Name + "," + modelName + "," + CashPrice.replace(",", "") + "," + Term + "," + First_Monthly_Payment.replace(",", "") + "," + Next_22_Monthly_Payments.replace(",", "") + "," + Customer_Deposit.replace(",", "") + ","
							+ CentreDepositContribution.replace(",", "") + "," + FinanceDepositAllowance.replace(",", "") + "," + Amount_of_Credit.replace(",", "") + "," + Guaranteed_Future_ValueOptional_Final_Payment.replace(",", "") + "," + Total_Amount_Payable.replace(",", "") + ","
							+ Fixed_Rate_of_Interest.replace(",", "") + "," + Representative_APR.replace(",", "") + "," + cfn.TimeStamp();
					//To move data to notepad and CSV file
					writer.println(finalOutput);
					writer.flush();

					csv.write(finalOutput + "\n");
					csv.flush();

					System.out.println("finalOutput :--->>> " + finalOutput);
					System.out.println("********************************************************************************************");

					Thread.sleep(1000);
					int BackBtnCount = driver.findElements(By.xpath("//*[@id='codeweaversPreviousStepButton']")).size();
					while (BackBtnCount < 1) {
						Thread.sleep(500);
						BackBtnCount = driver.findElements(By.xpath("//*[@id='codeweaversPreviousStepButton']")).size();
					}
					// Choose a different model or grade
					WebElement ChooseAnotherCar = driver.findElement(By.xpath("//*[@id='codeweaversPreviousStepButton']"));
					cfn.ClickUsingJavaScript(ChooseAnotherCar);
					// cfn.ClickByXpath("//*[@id='codeweaversPreviousStepButton']");
					Thread.sleep(2000);

				}
			}
			carindex++;
		}
		writer.close();
		csv.close();
		
       

	}
	
	

}
