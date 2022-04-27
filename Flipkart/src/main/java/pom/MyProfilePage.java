package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyProfilePage {

	@FindBy(xpath="//div[text()='Manage Addresses']")
	private WebElement manageAddresses;
	
	@FindBy(xpath="//div[text()='Saved Cards']")
	private WebElement savedCards;
	
	public MyProfilePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	public void clickOnManageAddresses()
	{
		manageAddresses.click();
	}
	
	public void clickOnSavedCards()
	{
		savedCards.click();
	}

}
