package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDescriptionPage {
	
	@FindBy(xpath="//div[@class='aMaAEs']//h1//span")
	private WebElement productTitle;

	@FindBy(xpath="//ul[@class='row']//li[1]//button")
	private WebElement addToCart;
	
	@FindBy(xpath="//ul[@class='row']//li[2]//button")
	private WebElement BuyNow;
	
	public ProductDescriptionPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnAddToCart()
	{
		addToCart.click();
	}
	
	public void clickOnBuyNow()
	{
		BuyNow.click();
	}
	
	public String getProductTitle()
	{
		String title=productTitle.getText();
		return title;
	}
}


