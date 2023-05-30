package package1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Property_File
{
	WebDriver driver;
	Properties con;
	
	@BeforeTest
	public void launchApp() throws Throwable, IOException
	{
		con = new Properties();
		con.load(new FileInputStream("OR.properties")); 		
		//System.setProperty("webdriver.chrome.driver", "Chromedriver.exe");
		driver  = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(con.getProperty("Url"));		
	}	
	@Test
	public void verifyLogin()
	{
		driver.findElement(By.xpath(con.getProperty("ObjReset"))).click();
		driver.findElement(By.xpath(con.getProperty("Objuser"))).sendKeys("EnterUser");
		driver.findElement(By.xpath(con.getProperty("Objpass"))).sendKeys("Enterpass");
		driver.findElement(By.xpath(con.getProperty("ObjLoginbtn"))).click();
		
		String expected_title = "Dashboard « Stock Accounting";
		String actual_title = driver.getTitle();
		if(expected_title.equalsIgnoreCase(actual_title))
		{
			Reporter.log("Login Success: "+expected_title+"     "+actual_title,true );
		}else
		{
			Reporter.log("Login Fail: "+expected_title+"     "+actual_title,true );
		}
	}
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}

}
