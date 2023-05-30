package package1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AdvanceReports 
{

	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	
	@BeforeTest
	public void generateReport()
	{
		report = new ExtentReports("./ExtentReports/Demo.html");
	}
	@BeforeMethod
	public void setUp()
	{
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://google.com");
	}
	@Test
	public void testcase1()
	{
		test = report.startTest("Pass Test");
		test.assignAuthor("Shiva");
		test.assignCategory("Functional Testing");
		String exptitle = "google";
		String acttitle = "Google";
		if(exptitle.equalsIgnoreCase(acttitle))
		{
			test.log(LogStatus.PASS, "Title is Matching:::"+exptitle+"         "+acttitle);
		}else
		{
			test.log(LogStatus.FAIL, "Title is Not Matching  :"+exptitle+"        "+acttitle);
		}
	}
	@Test
	public void testcase2()
	{
		test = report.startTest("Fail Test");
		test.assignAuthor("Shiva");
		test.assignCategory("Functional Testing");
		String exp_title = "Google";
		String act_title = "Krishna";
		if(exp_title.equalsIgnoreCase(act_title))
		{
			test.log(LogStatus.PASS, "Title is Matching:::"+exp_title+"         "+act_title);
		}else
		{
			test.log(LogStatus.FAIL, "Title is Not Matching  :"+exp_title+"        "+act_title);
		}
	}
	@AfterMethod
	public void tearDown()
	{
		report.endTest(test);
		report.flush();
		driver.quit();
	}
}
