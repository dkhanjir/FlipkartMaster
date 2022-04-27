package agile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pom.ApplicationHeaderPage;
import pom.ApplicationHomePage;
import pom.Cart;
import pom.ProductDescriptionPage;
import pom.ProductSearchResultPage;
import util.Utility;

public class TestClassAddProductToCart {
	
	private WebDriver driver;
	private ApplicationHomePage applicationHomePage;
	private ApplicationHeaderPage applicationHeaderPage;
	private ProductSearchResultPage productSearchResultPage;
	private ProductDescriptionPage productDescriptionPage;
	private Cart cart;
	SoftAssert softAssert;
	String testID;
	
	@BeforeTest
	public void launchTheBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "F:\\Software Testing\\Chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	@BeforeClass
	public void createObjectOfPOMClasses() 
	{
		 applicationHomePage = new ApplicationHomePage(driver);
		 applicationHeaderPage = new ApplicationHeaderPage(driver);
		 productSearchResultPage = new ProductSearchResultPage(driver);
		 productDescriptionPage = new ProductDescriptionPage(driver);
		 cart = new Cart(driver);
	}
	
	@BeforeMethod()
	public void loginToApplication() throws EncryptedDocumentException, IOException, InterruptedException
	{
		driver.get("https://www.flipkart.com/");
		applicationHomePage.closeLoginPopup();
		applicationHeaderPage.clickOnLogin();
		
		String username = Utility.getData("Credentials",1,1);
		String password = Utility.getData("Credentials",1,2);
		String product = Utility.getData("ProductsList",1,1);
		applicationHomePage.sendUsername(username);
		applicationHomePage.sendPassword(password);
		applicationHomePage.clickOnLoginButton();
		Thread.sleep(3000);
		applicationHeaderPage.sendProductNameToSearchBox(product);
		applicationHeaderPage.clickOnSearchButton();
		Thread.sleep(3000);
		productSearchResultPage.clickOnProduct();
		
	}
	
	@Test
	public void verifyAddToCartAndRemoveFromCartFunctionality() throws InterruptedException
	{
		 softAssert = new SoftAssert();
		 testID = "ADC101";
		 Thread.sleep(3000);
		 ArrayList<String> list = new ArrayList<String>(driver.getWindowHandles());
		 driver.switchTo().window(list.get(1));
		 String nameOfProductToBeAdded = productDescriptionPage.getProductTitle();
		
		 productDescriptionPage.clickOnAddToCart();
		 Thread.sleep(3000);
		 String url = driver.getCurrentUrl();
		 Assert.assertEquals(url,"https://www.flipkart.com/viewcart?otracker=PP_GoToCart");
		 driver.close();
		 driver.switchTo().window(list.get(0));
		 applicationHeaderPage.clickOnCart();
		 boolean result = cart.verifyVisibilityOfAddedProductInCart();
		 softAssert.assertEquals(result,true);
		 cart.clickOnAddedItem();
		 Thread.sleep(3000);
		 String nameOfProductAdded = productDescriptionPage.getProductTitle();
	
		 softAssert.assertEquals(nameOfProductAdded,nameOfProductToBeAdded);
		 
		 driver.navigate().back();
		 
		 Thread.sleep(3000);
	
		 cart.clickOnRemove();
		 cart.clickOnConfirmRemoveProductFromCart();
		 Thread.sleep(3000);
		 try
		 {
			 result = cart.verifyVisibilityOfShopNowButton();
			 softAssert.assertEquals(result,true);
		 }
		 catch(NoSuchElementException e)
		 {
			 softAssert.fail("Cart is not empty");
		 }
		 
		 softAssert.assertAll(); 
		 
		 
	}
	
	@Test
	public void verifyBuyNowFunctionality() throws InterruptedException
	{
		 testID = "ADC102";
		 Thread.sleep(3000);
		 ArrayList<String> list = new ArrayList<String>(driver.getWindowHandles());
		 driver.switchTo().window(list.get(1));
		 productDescriptionPage.clickOnBuyNow();
		 Thread.sleep(5000);
		 String url = driver.getCurrentUrl();
		 driver.close();
		 driver.switchTo().window(list.get(0));
		 Assert.assertEquals(url,"https://www.flipkart.com/checkout/init?otracker=search");
		 
		
	}
	
	@AfterMethod
	public void logoutFromTheApplication(ITestResult result) throws IOException
	{
		if(ITestResult.FAILURE== result.getStatus())
		 {
			Utility.captureScreenshot(driver, testID);
		 }
		applicationHeaderPage.moveCursorToUser();
		applicationHomePage.clickOnLogout();	
	}
	
	@AfterClass
	public void clearObjectOfPOMClasses()
	{
		 applicationHomePage = null;
		 applicationHeaderPage = null;
		 productSearchResultPage = null;
		 productDescriptionPage = null;
		 cart = null;
	}
	
	@AfterClass
	public void closeTheObject()
	{
		driver.quit();
		driver = null;
		System.gc();
	}

}
