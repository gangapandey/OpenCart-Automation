package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BasePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
	@Test(groups= {"sanity", "Regression"})
	public void verify_login()
	{
		try
		{
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
	
		//Login
		LoginPage lp=new LoginPage(driver);
		lp.setEmail("igangapandey@gmail.com");
		lp.setPassword("test1234");
		lp.clickLogin();
		
		//MyAccount
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		Assert.assertTrue(targetPage);//Assert.assertEquals(targetPage, true,"Login failed");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
	}
}
