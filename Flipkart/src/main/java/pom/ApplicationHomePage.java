package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApplicationHomePage {
	
	private WebDriver driver;
	private Actions actions;
	
	@FindBy(xpath="(//button[contains(@class, '_2KpZ6l')])[1]")
	private WebElement loginPopUpCloseButton;
	
	@FindBy(xpath="(//input[@type='text'])[2]")
	private WebElement usernameField;
	
	@FindBy(xpath="//input[@type='password']")
	private WebElement passwordField;
	
	@FindBy(xpath="(//button[@type='submit'])[2]")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[text()='My Profile']")
	private WebElement myProfile;
	
	@FindBy(xpath="//div[text()='Logout']")
	private WebElement logout;


	public ApplicationHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
	    actions = new Actions(driver);
	}
	
	public void sendUsername(String username) 
	{
		usernameField.sendKeys(username);
	}
	
	public void sendPassword(String password) 
	{
		passwordField.sendKeys(password);
	}
	
	public void clickOnLoginButton()
	{
		loginButton.click();
	}
	public void closeLoginPopup()
	{
	    loginPopUpCloseButton.click();
	}
	
	public void clickOnMyProfile()
	{
		actions.moveToElement(myProfile).click().build().perform();
	}
	
	public void clickOnLogout()
	{
		actions.moveToElement(logout).click().build().perform();
	}
}
