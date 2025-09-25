package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC004_SearchProduct extends BaseClass {
	
	@Test(groups="master")
	
	public void SearchFunctionality(String expectResult) {
		
		logger.info("*****Starting TC004_SearchProduct*****");
		
		try{
			HomePage hp=new HomePage(driver);
			
			hp.enterProductName();
			
			hp.ClickOnSearchIcon();
			
			SearchPage sp=new SearchPage(driver);
			sp.isSearchResultDsiplayed("iPhone");
			Assert.assertEquals(sp.isSearchResultDsiplayed("iPhone"), true);	
			
		}catch(Exception e) {
			Assert.fail();
		}
	}
	
	
	

}
