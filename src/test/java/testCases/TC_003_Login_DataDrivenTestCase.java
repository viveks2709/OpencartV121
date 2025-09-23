package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.Dataproviders;

public class TC_003_Login_DataDrivenTestCase extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=Dataproviders.class ,groups="Datadriven")//dataProviderClass:We used becoz getting dataProvider from different class
	public void verify_LoginDDT(String email, String pwd, String expctResult)
	{
		logger.info("***Started TC_003_Login_DataDrivenTestCase***");
		
		try
		{
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//MyAccount
		
		MyAccountPage ma=new MyAccountPage(driver);
		boolean targetPage=ma.isMyAccountPageExists();
		
		/* Data is valid -Login Success -Test Pass -logout
		 * Data is valid- login failed-Test failed
		 * 
		 * Data is invalid-Login Success-Test Failed-logout
		 * Data is invalid-login failed-Test Pass
		 */
		
		if(expctResult.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				ma.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(expctResult.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				ma.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***Finished TC_003_Login_DataDrivenTestCase***");
	}
	
	

}
