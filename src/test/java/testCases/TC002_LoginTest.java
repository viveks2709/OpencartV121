package testCases;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Sanity", "Master"})
	 public void verify_login()
	 {
		 logger.info("***Starting TC002_LoginTest*** ");
		 
		 try
		 {
		 //Home Page
		 HomePage hp=new HomePage(driver);
		 hp.clickMyAccount();
		 hp.clickLogin();
		 
		 //Login Page
		 LoginPage lp=new LoginPage(driver);
		 lp.setEmail(p.getProperty("email"));
		 lp.setPassword(p.getProperty("password"));
		 lp.clickLogin();
		 
		 //My account page
		 MyAccountPage map=new MyAccountPage(driver);
		 boolean targetpage=map.isMyAccountPageExists();
		 
		 Assert.assertTrue(targetpage);//Assert.assertEquals(targetpage, true,"Login Failed");
		 }
		 catch(Exception e)
		 {
			Assert.fail();
		 }
		 
		 logger.info("***Finished TC002_LoginTest*** ");
	 }

}
