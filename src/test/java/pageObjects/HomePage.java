package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	
	public HomePage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lmkMyaccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(linkText="Login")
	WebElement linklogin;
	
	@FindBy(xpath="//input[@placeholder='Search']")
	WebElement SearchBox;
	
	@FindBy(xpath="//i[@class='fa fa-search']")
	WebElement searchIcon;
	
	public void clickMyAccount()
	{
		lmkMyaccount.click();
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	}
	
	public void clickLogin()
	{
		linklogin.click();
	}
	
	public void enterProductName()
	{
		SearchBox.sendKeys("iPhone");
	}
	
	public void ClickOnSearchIcon()
	{
		searchIcon.click();
	}
}
