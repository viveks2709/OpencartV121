package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {
	
	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[text()='iPhone']")
	WebElement SearchResult;
	
	public boolean isSearchResultDsiplayed(String searchProductName) {
		
		
		try {
			
			return(SearchResult.isDisplayed());
		}
		catch(Exception e){
			return false;
			
		}
		
	}
	
	

}
