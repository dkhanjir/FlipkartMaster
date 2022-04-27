package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ManageAddressesPage {
	
	private WebDriver driver;
	private Actions actions;

	@FindBy(xpath="//button[text()='ADD ADDRESSES']")
	private WebElement addAddresses;
	
	@FindBy(xpath="//input[@name='name']")
	private WebElement name;
	
	@FindBy(xpath="//input[@name='phone']")
	private WebElement mobileNumber;
	
	@FindBy(xpath="//input[@name='pincode']")
	private WebElement pincode;
	
	@FindBy(xpath="//input[@name='addressLine2']")
	private WebElement locality;
	
	@FindBy(xpath="//textarea[@name='addressLine1']")
	private WebElement address;
	
	@FindBy(xpath="//input[@name='city']")
	private WebElement city;
	
	@FindBy(xpath="//select[@name='state']")
	private WebElement state;
	
	@FindBy(xpath="//option[text()='--Select State--']")
	private WebElement defaultStateOption;
	
	@FindBy(xpath="//input[@name='landmark']")
	private WebElement landmark;
	
	@FindBy(xpath="//input[@name='alternatePhone']")
	private WebElement alternatePhone;
	
	@FindBy(xpath="//input[@id='HOME']")
	private WebElement homeRadioButton;
	
	@FindBy(xpath="//input[@id='WORK']")
	private WebElement workRadioButton;
	
	@FindBy(xpath="//button[text()='Save']")
	private WebElement saveAddress;
	
	@FindBy(xpath="//button[text()='Cancel']")
	private WebElement cancelAddingNewAddress;
	
	@FindBy(xpath="(//div[@class='umgxnI'])[1]")
	private WebElement modifyAddressIcon;
	
	@FindBy(xpath="(//span[text()='Edit'])[1]")
	private WebElement editExistingAddress;
	
	@FindBy(xpath="(//span[text()='Delete'])[1]")
	private WebElement deleteExistingAddress;
	
	@FindBy(xpath="//button[text()='YES, DELETE']")
	private WebElement confirmDeletingSavedAddress;
	
	@FindBy(xpath="//button[text()='CANCEL']")
	private WebElement cancelDeletingSavedAddress;
	
	public ManageAddressesPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
		this.driver = driver;
		actions = new Actions(driver);
	}
	
	public void clickOnAddAddresses()
	{
		addAddresses.click();
	}
	
	public void sendName(String coustomerName)
	{
		name.sendKeys(coustomerName);
	}
	
	public void sendMobileNumber(String phoneNumber)
	{
		mobileNumber.sendKeys(phoneNumber);
	}
	
	public void sendPincode(String pincodeValue)
	{
		pincode.sendKeys(pincodeValue);
	}
	
	public void sendLocality(String localityInformation)
	{
		locality.sendKeys(localityInformation);
	}
	
	public void sendAddress(String detailAddress)
	{
		address.sendKeys(detailAddress);
	}
	
	public void sendCityOrDistrictOrTownName(String CityName)
	{
		city.sendKeys(CityName);
	}
	
	public void selectState(String stateName)
	{
		Select select = new Select(state);
		select.selectByVisibleText(stateName);		
	}
	
	public void sendLandmark(String LandMarkDetails)
	{
		landmark.sendKeys(LandMarkDetails);
	}
	
	public void sendAlternatePhoneNumber(String alternateNumber)
	{
		alternatePhone.sendKeys(alternateNumber);
	}
	
	public void selectHomeRadio()
	{
		actions.moveToElement(homeRadioButton).click().build().perform();
	}
	
	public void selectWorkRadio()
	{
		actions.moveToElement(workRadioButton).click().build().perform();
	}
	
	public void clickOnSaveAddress()
	{
		saveAddress.click();
	}
	
	public void clickOnCancelAddingAddress()
	{
		cancelAddingNewAddress.click();
	}
	
	public void moveToModifyAddressIcon()
	{
		actions.moveToElement(modifyAddressIcon).perform();
	}
	
	public void clickOnEditExistingAddress()
	{
		editExistingAddress.click();
	}
	
	public void clickOnDeleteExistingAddress()
	{
		deleteExistingAddress.click();
	}
	public void clickOnConfirmDeletingSavedAddress()
	{
		confirmDeletingSavedAddress.click();
	}
	public void clickOnCancelDeletingSavedAddress()
	{
		cancelDeletingSavedAddress.click();
	}
	
	public boolean verifyAutomaticStateSelection()
	{
		boolean result = defaultStateOption.isDisplayed();
		return result;
	}
	
	public boolean verifyifHomeRadioIsSelected()
	{
		boolean result = homeRadioButton.isSelected();
		return result;
	}
	
	public boolean verifyifWorkRadioIsSelected()
	{
		boolean result = workRadioButton.isSelected();
		return result;
	}
	
	public String verifyAutomaticCitySelection()
	{
		String result = city.getAttribute("value");
		return result;
	}
	
	public boolean verifyIfModifyAddressIconIsVisibleOrNot()
	{
		boolean result = modifyAddressIcon.isDisplayed();
		return result;
	}
	
	public boolean verifyIfAddAddressButtonIsVisibleOrNot()
	{
		boolean result = addAddresses.isDisplayed();
		return result;
	}
}
