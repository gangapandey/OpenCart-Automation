package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass 
{
	public static WebDriver driver;
	
	@BeforeClass(groups= {"sanity", "Regression", "Master", "DataDriven"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br)
	{
		switch(br.toLowerCase())
		{
		case "chrome" :driver = new ChromeDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		default: System.out.println("Invalid Browser Name"); return;
		}
	    
	
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"sanity", "Regression", "Master", "DataDriven"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomNumber()
	{
		String generatednumber=RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	public String randomAlphaNumberic()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(3);
		String generatednumber=RandomStringUtils.randomNumeric(3);
		return (generatedstring+"@"+generatednumber);
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

}
