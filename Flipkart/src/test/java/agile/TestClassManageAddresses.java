package agile;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pom.ApplicationHeaderPage;
import pom.ApplicationHomePage;
import pom.ManageAddressesPage;
import pom.MyProfilePage;
import pom.ProductSearchResultPage;
import util.Utility;

public class TestClassManageAddresses {
	
	private WebDriver driver;
	private ApplicationHomePage applicationHomePage;
	private ApplicationHeaderPage applicationHeaderPage;
	private MyProfilePage myProfilePage;
	private ManageAddressesPage manageAddressesPage;
	
	SoftAssert softAssert;
	String testID;
	
	@BeforeTest
	public void launchTheBrowser()
	{
		 System.setProperty("webdriver.chrome.driver", "F:\\Software Testing\\Chrome\\chromedriver.exe");
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	
	@BeforeClass
	public void createObjectOfPOMClasses()
	{
		 applicationHomePage = new ApplicationHomePage(driver);
		 applicationHeaderPage = new ApplicationHeaderPage(driver);
		 myProfilePage = new MyProfilePage(driver);
		 manageAddressesPage = new ManageAddressesPage(driver);
		
	}
	
	@BeforeMethod
	public void loginIntoApplication() throws InterruptedException, EncryptedDocumentException, IOException
	{
		
		driver.get("https://www.flipkart.com/");
		applicationHomePage.closeLoginPopup();
		applicationHeaderPage.clickOnLogin();
		
		String username = Utility.getData("Credentials",1,1);
		String password = Utility.getData("Credentials",1,2);
		applicationHomePage.sendUsername(username);
		applicationHomePage.sendPassword(password);
		applicationHomePage.clickOnLoginButton();
		Thread.sleep(5000);
		applicationHeaderPage.moveCursorToUser();
		applicationHomePage.clickOnMyProfile();
		myProfilePage.clickOnManageAddresses();
		
		
	}
	
	@Test
	public void verifyFunctionalityOfElementsPresentOnSaveAddressTab() throws InterruptedException, EncryptedDocumentException, IOException
	{
		  testID = "MA101";
		  softAssert = new SoftAssert();
		  //Thread.sleep(3000);
		  manageAddressesPage.clickOnAddAddresses();
		  boolean result = manageAddressesPage.verifyifHomeRadioIsSelected();
		  softAssert.assertEquals(result, false);
		  
		  result= manageAddressesPage.verifyifWorkRadioIsSelected();
		  softAssert.assertEquals(result, false);
		  //Thread.sleep(3000);
		  manageAddressesPage.selectHomeRadio();
		  //Thread.sleep(3000);
		  result = manageAddressesPage.verifyifHomeRadioIsSelected();
		  softAssert.assertEquals(result, true);
		  
		  manageAddressesPage.selectWorkRadio();
		  //Thread.sleep(3000);
		  result= manageAddressesPage.verifyifWorkRadioIsSelected();
		  softAssert.assertEquals(result, true);
		  
		  
		  String data = manageAddressesPage.verifyAutomaticCitySelection();
		  System.out.println(data+"ram");
		  softAssert.assertEquals(data, "");
		  result = manageAddressesPage.verifyAutomaticStateSelection();
		  softAssert.assertEquals(result, true);
		  manageAddressesPage.sendPincode(Utility.getData(data, 1, 3));
		  Thread.sleep(3000);
		  
		  data = manageAddressesPage.verifyAutomaticCitySelection();
		  System.out.println(data);
		  softAssert.assertNotEquals(data,null);
		  result = manageAddressesPage.verifyAutomaticStateSelection();
		  softAssert.assertEquals(result, true);
		  
		  softAssert.assertAll();
	}
	@Test
	public void verifyIfAddressIsSuccessfullySavedOrNot() throws EncryptedDocumentException, IOException, InterruptedException
	{
		manageAddressesPage.clickOnAddAddresses();
		Thread.sleep(3000);	
		manageAddressesPage.sendName(Utility.getData("AddressSaveData",1,1));
		manageAddressesPage.sendMobileNumber(Utility.getData("AddressSaveData",1,2));
		manageAddressesPage.sendPincode(Utility.getData("AddressSaveData",1,3));
		manageAddressesPage.sendLocality(Utility.getData("AddressSaveData",1,4));
		manageAddressesPage.sendAddress(Utility.getData("AddressSaveData",1,5));
		manageAddressesPage.sendLandmark(Utility.getData("AddressSaveData",1,8));
		manageAddressesPage.sendAlternatePhoneNumber(Utility.getData("AddressSaveData",1,9));
		manageAddressesPage.selectHomeRadio();
		manageAddressesPage.clickOnSaveAddress();
		Thread.sleep(3000);
		
		boolean result = manageAddressesPage.verifyIfModifyAddressIconIsVisibleOrNot();
		Assert.assertEquals(result,true);
	}
	
	@Test
	public void verifyRemoveSavedAddressFunctionality() throws InterruptedException
	{
		Thread.sleep(3000);	
		manageAddressesPage.moveToModifyAddressIcon();
		manageAddressesPage.clickOnDeleteExistingAddress();
		manageAddressesPage.clickOnConfirmDeletingSavedAddress();
        Thread.sleep(3000);
		
		boolean result = manageAddressesPage.verifyIfAddAddressButtonIsVisibleOrNot();
		Assert.assertEquals(result,true);
		
	}
	
	@AfterMethod                     //testNG Listener//
	public void logoutFromApplication(ITestResult result) throws IOException, InterruptedException
	{
		if(ITestResult.FAILURE== result.getStatus())
		{
		    Utility.captureScreenshot(driver, testID);
		}
		applicationHeaderPage.moveCursorToUser();
		applicationHomePage.clickOnLogout();
		Thread.sleep(5000);
	}
	
	@AfterClass
	public void clearTheObjectReferencesOfPOMClasses()
	{
		 applicationHomePage = null;
		 applicationHeaderPage = null;
		 myProfilePage = null;
		 manageAddressesPage = null;
	}
	
	@AfterTest
	public void closeTheBrowser()
	{
		 driver.quit();
		 driver = null;
		 System.gc();
	}

}
