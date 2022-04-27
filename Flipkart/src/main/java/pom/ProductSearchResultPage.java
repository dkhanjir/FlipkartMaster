package pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class ProductSearchResultPage {
	
	@FindBy(xpath="((//div[@class='_1AtVbE col-12-12'])[4]//a//div[1])[9]")
	private WebElement productName;

	@FindBy(xpath="(//div[@class='_1AtVbE col-12-12'])[4]//a//ul")
	private WebElement productDescription;
	
	@FindBy(xpath="((//div[@class='_1AtVbE col-12-12'])[4]//a//div[1])[13]")
	private WebElement productPrice;
	
	public ProductSearchResultPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnProduct()
	{
		productName.click();
	}
	
	public String getProductName()
	{
		String data = productName.getText();
		return data;
	}
	
	public String getProdcutDescription()
	{
		String data = productDescription.getText();
		return data;
	}
	
	public String getProductPrice()
	{
		String data = productPrice.getText();
		return data;
	}
}
