package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApplicationHeaderPage {
	
	private WebDriver driver;
	private Actions actions;

	@FindBy(xpath="//a[text()='Login']")
	private WebElement login;
	
	@FindBy(xpath="//span[text()='Cart']")
	private WebElement cart;
	
	@FindBy(xpath="//div[text()='More']")
	private WebElement more;
	
	@FindBy(xpath="//input[contains(@title,'Search for products')]")
	private WebElement productSerachBox;
	
	@FindBy(xpath="(//button[@type='submit'])[1]")
	private WebElement serachButton;
	
	@FindBy(xpath="//div[@class='exehdJ']")
	private WebElement user;
	
	public ApplicationHeaderPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
		actions = new Actions(driver);
	}
	
	public void moveCursorToUser()
	{
		actions.moveToElement(user).perform();
	}
	
	public void clickOnLogin()
	{
		actions.moveToElement(login).click().build().perform();
	}
	
	public void sendProductNameToSearchBox(String prodcutName)
	{
		productSerachBox.sendKeys(prodcutName);
	}
	
	public void clickOnSearchButton()
	{
		serachButton.click();
	}
	
	public void clickOnCart()
	{
		cart.click();
	}
}
