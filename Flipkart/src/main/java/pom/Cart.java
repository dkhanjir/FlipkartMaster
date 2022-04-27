package pom;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Cart {
	
	private WebDriver driver;
	JavascriptExecutor js;
	
	@FindBy(xpath ="(//div[@class='_2nQDXZ']//div)[3]")
	private WebElement addedItem;
	
	@FindBy(xpath ="//div[text()='Save for later']")
	private WebElement saveForLater;
	
	@FindBy(xpath ="(//div[text()='Remove'])[1]")
	private WebElement remove;
	
	@FindBy(xpath ="(//div[text()='Cancel'])[1]")
	private WebElement cancelRemoveProductFromCart;
	
	@FindBy(xpath ="(//div[text()='Remove'])[1]")
	private WebElement confirmRemoveProductFromCart;
	
	@FindBy(xpath ="//div[@id='container']//button[1]")
	private WebElement cancelConfirmRemoveProductFromCartPopup;
	
	@FindBy(xpath ="//span[text()='Shop now']")
	private WebElement shopNow;
	
	public Cart(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
		this.driver = driver;
		js = (JavascriptExecutor)driver;
	}
	
	public String getNameOfProductPresentInTheCart()
	{
		String text = addedItem.getText();
		return text;
	}
	
	public boolean verifyVisibilityOfAddedProductInCart()
	{
		Boolean result = addedItem.isDisplayed();
		return result;
	}
	
	public void clickOnAddedItem()
	{
		addedItem.click();
	}
	
	public void clickOnRemove()
	{
		js.executeScript("arguments[0].scrollIntoView(true);",remove);
		remove.click();
	}
	
	public void clickOnConfirmRemoveProductFromCart()
	{
		confirmRemoveProductFromCart.click();
	}
	
	public boolean verifyVisibilityOfShopNowButton()
	{
		boolean result = shopNow.isDisplayed();
		return result;
	}

}
